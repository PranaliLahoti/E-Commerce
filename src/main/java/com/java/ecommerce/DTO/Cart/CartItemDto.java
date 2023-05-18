package com.java.ecommerce.DTO.Cart;

import com.java.ecommerce.DTO.ProductDto;

public class CartItemDto {
    
    private Integer id;
    private ProductDto product;
    private Integer quantity;


    public CartItemDto(Integer id, ProductDto product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }


    public Integer getId() {
        return id;
    }


    public ProductDto getProduct() {
        return product;
    }


    public Integer getQuantity() {
        return quantity;
    }

}
