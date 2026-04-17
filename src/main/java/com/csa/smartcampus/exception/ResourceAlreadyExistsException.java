package com.csa.smartcampus.exception;

public class ResourceAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
    
    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
