package com.example.projet.exception;

public class ProduitsNotFoundException extends RuntimeException {
    public ProduitsNotFoundException(String message) {
        super(message);
    }

}
