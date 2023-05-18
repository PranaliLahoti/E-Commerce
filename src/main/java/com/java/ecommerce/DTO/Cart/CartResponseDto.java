package com.java.ecommerce.DTO.Cart;

import java.util.List;

public class CartResponseDto {

    private List<CartItemDto>cartItems;
    private Integer totalCost;

    public CartResponseDto(List<CartItemDto> cartItems, Integer totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }

    public List<CartItemDto> getcartItems() {
        return cartItems;
    }
    public Integer gettotalCost() {
        return totalCost;
    }
    
}
