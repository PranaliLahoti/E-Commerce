package com.java.ecommerce.Common;


import com.java.ecommerce.DTO.Cart.CartResponseDto;

public class CartResponse {
    private final boolean success;
    private final String message;
    private CartResponseDto cartResponse;

    
    public CartResponse(boolean success, String message, CartResponseDto cartResponse) {
        this.success = success;
        this.message = message;
        this.cartResponse = cartResponse;
    }

    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public CartResponseDto getCartResponse() {
        return cartResponse;
    }
    
    
}
