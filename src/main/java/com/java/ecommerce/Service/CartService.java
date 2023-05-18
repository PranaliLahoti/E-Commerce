package com.java.ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.Model.Cart;
import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Repository.CartRepo;

import jakarta.transaction.Transactional;

@Service
public class CartService {
    
    @Autowired
    CartRepo cartRepo;

    public boolean isProductPresentinUserCart(User user, Product product) {
        return cartRepo.existsByUserAndProduct(user, product);
    }

    public void addToCart(User user, Product product, int quantity) {
        Cart cart = new Cart(user, product, quantity);
        cartRepo.save(cart);
    }

    @Transactional
    public void removeProductInUserCart(User user, Integer productId) {
        cartRepo.deleteByUserAndProduct_Id(user, productId);
    }

    public void updateCartQuantity(User user, Product product, int quantity) {
        Cart cart = cartRepo.findByUserAndProduct(user,product).get();
        cart.setQuantity(quantity);
        cartRepo.save(cart);
    }

    public List<Cart> fetchAllUserCarts(User user) {
        return cartRepo.findAllByUserOrderByCreatedAtDesc(user);
    }
}
