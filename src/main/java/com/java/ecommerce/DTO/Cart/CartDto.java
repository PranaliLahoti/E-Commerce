package com.java.ecommerce.DTO.Cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartDto {
    
    @NotNull
    private int userId;

    @NotNull
    private int productId;
    
    @Positive
    private int quantity;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
