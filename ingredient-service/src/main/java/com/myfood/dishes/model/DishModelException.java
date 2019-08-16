package com.myfood.dishes.model;

/**
 * Created by rakov on 15.08.2019.
 */
public class DishModelException extends RuntimeException {
    public DishModelException() {
    }


    public DishModelException(String message) {
        super(message);
    }


    public DishModelException(String message, Throwable cause) {
        super(message, cause);
    }


    public DishModelException(Throwable cause) {
        super(cause);
    }
}
