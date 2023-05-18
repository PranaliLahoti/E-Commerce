package com.java.ecommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ecommerce.Model.Cart;
import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Model.User;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer>{

    // check if product is present in user cart
    boolean existsByUserAndProduct(User user, Product product);

    //get all products in user cart
    List<Cart> findAllByUserOrderByCreatedAtDesc(User user);

    //  Method to delete a product from user's cart
    void deleteByUserAndProduct_Id(User user, int productId);

    //get cart by user and product
    Optional<Cart> findByUserAndProduct(User user,Product product);
       
    
}
