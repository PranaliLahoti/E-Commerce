package com.java.ecommerce.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.Common.APIResponse;
import com.java.ecommerce.Common.WishListResponse;
import com.java.ecommerce.DTO.ProductDto;
import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Model.WishList;
import com.java.ecommerce.Service.ProductService;
import com.java.ecommerce.Service.UserService;
import com.java.ecommerce.Service.WishListService;


@RestController
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/wishlists/{userId}/products/{productId}")
    public ResponseEntity<APIResponse> addToWishlist( @PathVariable Integer userId,@PathVariable Integer productId){

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
        
        //check if product already in user repo
        if(wishListService.isProductPresentinWishlist(user,product)){
            return new ResponseEntity<>(new APIResponse(false, "product already present to wishlist"),HttpStatus.CONFLICT);
        }
        //add to wishlist
        wishListService.addProductToUserWishlist(user,product);
        //return success response

        return new ResponseEntity<APIResponse>(new APIResponse(true, "Successfully added product to wishlist"), HttpStatus.OK);

    }

    @GetMapping(path = "/wishlists/{userId}")
    public ResponseEntity<WishListResponse> getWishListProducts(@PathVariable Integer userId){
        //check if user present - if not return user not present error
        if(!userService.isuserIdPresent(userId)){
            return new ResponseEntity<>(new WishListResponse(false, "user id not present",null),HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserById(userId);
        List<WishList> wishlists= wishListService.getUserWishlistProducts(user);
        List<ProductDto> products = new ArrayList<>();
        for(WishList w: wishlists){
            products.add(productService.getProductDto(w.getProduct()));
        }
        return new ResponseEntity<>(new WishListResponse(true, "Wishlists products", products),HttpStatus.OK);
    }

    @DeleteMapping(path = "/wishlists/{userId}/products/{productId}")
    public ResponseEntity<APIResponse> deleteProductInUSerWishlist(@PathVariable Integer userId, @PathVariable Integer productId){
        if(!userService.isuserIdPresent(userId)){
            return new ResponseEntity<>(new APIResponse(false, "user id not present"),HttpStatus.NOT_FOUND); 
        }
        if(!productService.isProductPresent(productId)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present"),HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserById(userId);

        Product product = productService.getProduct(productId);

        if(!wishListService.isProductPresentinWishlist(user, product)){
            return new ResponseEntity<>(new APIResponse(false, "product id not present in user wishlist"),HttpStatus.NOT_FOUND);
        }
        wishListService.removeProductInUserWishlist(user,productId);

        return new ResponseEntity<APIResponse>(new APIResponse(true, "Successfully removed product from user wishlist"), HttpStatus.OK);

    }
    
}
