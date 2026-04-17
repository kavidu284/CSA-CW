package com.csa.smartcampus.exception;

public class RoomNotEmptyException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public RoomNotEmptyException(String message) {
        super(message);
    }
    
    public RoomNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
