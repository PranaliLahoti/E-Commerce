package com.java.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Model.User;
import com.java.ecommerce.Model.WishList;

@Repository
public interface WishListRepo extends JpaRepository<WishList,Integer>{

    // check if product is present in user wishlist
    boolean existsByUserAndProduct(User user, Product product);

    //get all products in user wishlist
    List<WishList> findAllByUserOrderByCreatedAtDesc(User user);

    //  Method to delete a product from user's wishlist
    void deleteByUserAndProduct_Id(User user, int productId);
}
