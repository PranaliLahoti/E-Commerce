package com.java.ecommerce.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginDto {
    
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 5,message = "password should be at least 5 characters long")
    private String Password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    
}
