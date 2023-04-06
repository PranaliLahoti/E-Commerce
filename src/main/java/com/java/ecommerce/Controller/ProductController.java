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
import com.java.ecommerce.DTO.ProductDto;
import com.java.ecommerce.Model.Category;
import com.java.ecommerce.Service.CategoryService;
import com.java.ecommerce.Service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "/products")
    public ResponseEntity<APIResponse> createProduct(@Valid @RequestBody ProductDto productDto){
        if(!categoryService.isCategoryPresent(productDto.getCategoryId())){
            return new ResponseEntity<>(new APIResponse(false, "Category ID not present"),HttpStatus.NOT_FOUND);
        }

        Category category = categoryService.getCategory(productDto.getCategoryId());
        productService.createProduct(productDto,category);
        return new ResponseEntity<>(new APIResponse(true, "Product added successfully"),HttpStatus.CREATED);
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAll();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PutMapping(path="/products/{productId}")
    public ResponseEntity<APIResponse> updateProduct(@PathVariable Integer productId,@Valid @RequestBody ProductDto productDto){
        if(!productService.isProductPresent(productId)){
            return new ResponseEntity<>(new APIResponse(false, "Product id does not exists"),HttpStatus.NOT_FOUND);
        }
        if(!categoryService.isCategoryPresent(productDto.getCategoryId())){
            return new ResponseEntity<>(new APIResponse(false, "Category ID not present"),HttpStatus.NOT_FOUND);
        }
        Category category = categoryService.getCategory(productDto.getCategoryId());
        productService.updateProduct(productDto,category,productId);
        return new ResponseEntity<>(new APIResponse(true, "Product updated successfully"),HttpStatus.OK);
    }

    @DeleteMapping(path="/products/{productId}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Integer productId){
        if(!productService.isProductPresent(productId)){
            return new ResponseEntity<>(new APIResponse(false, "Product id does not exists"),HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new APIResponse(true, "Product deleted successfully"),HttpStatus.OK);
    }
}
