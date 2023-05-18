package com.java.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.Common.APIResponse;
import com.java.ecommerce.Common.CartResponse;
import com.java.ecommerce.DTO.ProductDto;
import com.java.ecommerce.DTO.Cart.CartDto;
import com.java.ecommerce.DTO.Cart.CartItemDto;
import com.java.ecommerce.DTO.Cart.CartResponseDto;
import com.java.ecommerce.DTO.Cart.UpdateCartDto;
import com.java.ecommerce.Model.Cart;
import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Service.CartService;
import com.java.ecommerce.Service.ProductService;
import com.java.ecommerce.Service.UserService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping(path = "/carts")
    public ResponseEntity<APIResponse> addToCart(@Valid @RequestBody CartDto cartDto){

        int userId = cartDto.getUserId();
        int productId = cartDto.getProductId();
        int quantity = cartDto.getQuantity();

        //check if user present - if not return user not present error

        if(!userService.isuserIdPresent(userId)){
            return new ResponseEntity<>(new APIResponse(false, "user id not present"),HttpStatus.NOT_FOUND);
        }
        //check if product present - if not return product not found error
        if(!productService.isProductPresent(productId)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present"),HttpStatus.NOT_FOUND);
        }
        //get user by user id

        User user = userService.getUserById(userId);

        //get product by product id
        Product product = productService.getProduct(productId);

        //check if product already added in cart
        if(cartService.isProductPresentinUserCart(user,product)){
            return new ResponseEntity<>(new APIResponse(false, "product already present in cart"),HttpStatus.CONFLICT);
        }
        //add product in cart
        cartService.addToCart(user,product,quantity);

        return new ResponseEntity<APIResponse>(new APIResponse(true, "Successfully added product to cart"), HttpStatus.OK);

    }

    @GetMapping(path="/carts/{userId}")
    public ResponseEntity<CartResponse> getAllCartsofUser(@PathVariable Integer userId){
        //check if user present - if not return user not present error
        if(!userService.isuserIdPresent(userId)){
            return new ResponseEntity<>(new CartResponse(false, "user id not present",null),HttpStatus.NOT_FOUND);
        }
        //get user by id
        User user = userService.getUserById(userId);

        //get all carts for user
        List<Cart> carts = cartService.fetchAllUserCarts(user);

        //add carts in cartItem
        List<CartItemDto> cartItems = new ArrayList<>();

        int totalCost = 0;
        for(Cart c:carts){
            int cartId = c.getId();
            ProductDto product = productService.getProductDto(c.getProduct());
            double productPrice = product.getPrice();
            int quantity = c.getQuantity();
            totalCost+=(productPrice*quantity);
            CartItemDto cartItemDto = new CartItemDto(cartId, product, quantity);
            cartItems.add(cartItemDto);
        }
        CartResponseDto cartResponse = new CartResponseDto(cartItems, totalCost);
        
        return new ResponseEntity<>(new CartResponse(true, "Cart products", cartResponse),HttpStatus.OK);
    }

    @PutMapping(path="/carts/{userId}/products/{productId}")
    public ResponseEntity<APIResponse> updateCartQuantity(@PathVariable Integer userId, @PathVariable Integer productId, @Valid @RequestBody UpdateCartDto updateCartDto){

        //userId not present
        if(!userService.isuserIdPresent(userId)){
            return new ResponseEntity<>(new APIResponse(false, "user id not present"),HttpStatus.NOT_FOUND); 
        }

        //product Id not present
        if(!productService.isProductPresent(productId)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present"),HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserById(userId);

        Product product = productService.getProduct(productId);

        //product not present in user cart
        if(!cartService.isProductPresentinUserCart(user, product)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present in user cart"),HttpStatus.NOT_FOUND);
        }

        //update product detail
        int quantity = updateCartDto.getQuantity();
        cartService.updateCartQuantity(user,product,quantity);
        
        return new ResponseEntity<APIResponse>(new APIResponse(true, "Successfully updated product quantity in user cart"), HttpStatus.OK);
    }

    @DeleteMapping(path = "/carts/{userId}/products/{productId}")
    public ResponseEntity<APIResponse> deleteProductInUSerCart(@PathVariable Integer userId, @PathVariable Integer productId){

        //userId not present
        if(!userService.isuserIdPresent(userId)){
            return new ResponseEntity<>(new APIResponse(false, "user id not present"),HttpStatus.NOT_FOUND); 
        }

        //product Id not present
        if(!productService.isProductPresent(productId)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present"),HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserById(userId);

        Product product = productService.getProduct(productId);

        //product not present in user cart
        if(!cartService.isProductPresentinUserCart(user, product)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present in user cart"),HttpStatus.NOT_FOUND);
        }
        cartService.removeProductInUserCart(user,productId);

        return new ResponseEntity<APIResponse>(new APIResponse(true, "Successfully removed product from user cart"), HttpStatus.OK);

    }
    
}
