package com.java.ecommerce.DTO.Cart;

import jakarta.validation.constraints.Positive;

public class UpdateCartDto {
    
    @Positive
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

}
