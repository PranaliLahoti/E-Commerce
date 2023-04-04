package com.java.ecommerce.Exception;

import java.time.LocalDate;

public class ErrorStructure {
    private LocalDate timeStamp;
    private String message;
    private String details;
    
    public ErrorStructure(LocalDate timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
    
    public LocalDate getTimeStamp() {
        return timeStamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
}
