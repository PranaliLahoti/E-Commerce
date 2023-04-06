package com.java.ecommerce.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.DTO.ProductDto;
import com.java.ecommerce.Model.Category;
import com.java.ecommerce.Model.Product;
import com.java.ecommerce.Repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepo.save(product);
    }

    public ProductDto getProductDto(Product p){
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setProductName(p.getProductName());
        productDto.setDescription(p.getDescription());
        productDto.setPrice(p.getPrice());
        productDto.setCategoryId(p.getCategory().getId());
        return productDto;
    }
    public List<ProductDto> getAll() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product p:products){
            productDtos.add(getProductDto(p));
        }
        return productDtos;
    }
    public boolean isProductPresent(int id) {
        return productRepo.findById(id).isPresent();
    }

    public void updateProduct(ProductDto productDto, Category category, Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
        Product newProduct = product.get();
        newProduct.setProductName(productDto.getProductName());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setCategory(category);
        productRepo.save(newProduct);
    }

    public void deleteProduct(Integer productId) {
        productRepo.deleteById(productId);
    }
}
