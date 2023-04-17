package com.java.ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Model.WishList;
import com.java.ecommerce.Repository.WishListRepo;

import jakarta.transaction.Transactional;

@Service
public class WishListService {
    
    @Autowired
    WishListRepo wishListRepo;

    public void addProductToUserWishlist(User user, Product product) {
        WishList wishList = new WishList(user, product);
        wishListRepo.save(wishList);
    }

    public boolean isProductPresentinWishlist(User user, Product product) {
        return wishListRepo.existsByUserAndProduct(user, product);
    }

    public List<WishList> getUserWishlistProducts(User user) {
        List<WishList> wishLists = wishListRepo.findAllByUserOrderByCreatedAtDesc(user);
        return wishLists;
    }

    @Transactional
    public void removeProductInUserWishlist(User user, Integer productId) {

        wishListRepo.deleteByUserAndProduct_Id(user, productId);
    }
}
