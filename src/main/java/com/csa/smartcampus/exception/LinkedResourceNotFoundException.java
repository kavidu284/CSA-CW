package com.csa.smartcampus.exception;

public class LinkedResourceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public LinkedResourceNotFoundException(String message) {
        super(message);
    }
    
    public LinkedResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
