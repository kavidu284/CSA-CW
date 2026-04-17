package com.csa.smartcampus.exception;

public class SensorUnavailableException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public SensorUnavailableException(String message) {
        super(message);
    }
    
    public SensorUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
