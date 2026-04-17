package com.csa.smartcampus.util;

import java.io.Serializable;

public class ApiError implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String errorCode;
    private String message;
    private int statusCode;
    
    public ApiError() {
    }
    
    public ApiError(String errorCode, String message, int statusCode) {
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
    }
    
    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
