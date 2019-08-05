package com.myfood.ingredients.service;

public class InternalIngredientException extends RuntimeException {
    public InternalIngredientException() {
    }

    public InternalIngredientException(String message) {
        super(message);
    }

    public InternalIngredientException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalIngredientException(Throwable cause) {
        super(cause);
    }
}
