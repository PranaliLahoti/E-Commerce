package com.java.ecommerce.DTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {
    
    private Integer id;

    @NotEmpty
    @Size(min = 2,message = "product name should be at least 2 characters long")
    @Column(name="product_name")
    private String productName;

    @NotEmpty
    @Size(min = 2,message = "product description should be at least 2 characters long")
    private String description;

    @Positive
    private double price;

    @NotNull
    private Integer categoryId;
    
    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    
}
