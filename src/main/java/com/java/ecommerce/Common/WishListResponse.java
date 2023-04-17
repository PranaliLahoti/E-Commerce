package com.java.ecommerce.Common;

import java.time.LocalDateTime;
import java.util.List;

import com.java.ecommerce.DTO.ProductDto;

public class WishListResponse {
    private final boolean success;
    private final String message;
    private List<ProductDto> products;
    
    public WishListResponse(boolean success, String message, List<ProductDto> products) {
        this.success = success;
        this.message = message;
        this.products = products;
    }
    public List<ProductDto> getProducts() {
        return products;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
