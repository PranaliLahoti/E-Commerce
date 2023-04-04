package com.java.ecommerce.Controller;

import java.util.List;


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
import com.java.ecommerce.Model.Category;
import com.java.ecommerce.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @PostMapping(path="/categories")
    public ResponseEntity<APIResponse> createCategory(@Valid @RequestBody Category category){
        categoryService.createNewCategory(category);
        return new ResponseEntity<>(new APIResponse(true, "Category Created Successfully"),HttpStatus.CREATED);
    }

    @GetMapping(path="/categories")
    public List<Category> getAll(){
        return categoryService.fetchAllCategories();
    }

    @PutMapping(path = "/categories/{id}")
    public ResponseEntity<APIResponse> updateCategaory(@PathVariable int id, @Valid @RequestBody Category category){
        if(!categoryService.isCategoryPresent(id)){
            return new ResponseEntity<>(new APIResponse(false, "Category id does not exists"),HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(id, category);
        return new ResponseEntity<>(new APIResponse(true, "Category Updated Successfully"),HttpStatus.OK);
    }

    @DeleteMapping(path = "/categories/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable int id){
        if(!categoryService.isCategoryPresent(id)){
            return new ResponseEntity<>(new APIResponse(false, "Category id does not exists"),HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(new APIResponse(true, "Category Deleted Successfully"),HttpStatus.OK);
    }

}
