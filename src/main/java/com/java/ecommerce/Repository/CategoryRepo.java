package com.java.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ecommerce.Model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{
    
}
