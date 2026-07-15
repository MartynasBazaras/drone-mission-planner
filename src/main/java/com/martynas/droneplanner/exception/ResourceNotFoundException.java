package com.martynas.droneplanner.exception;

// Thrown when a requested resource does not exist
public class ResourceNotFoundException extends RuntimeException {

    // Create exception with a custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}