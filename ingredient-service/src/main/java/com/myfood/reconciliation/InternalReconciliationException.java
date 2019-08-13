package com.myfood.reconciliation;

/**
 * Created by rakov on 13.08.2019.
 */
public class InternalReconciliationException extends RuntimeException {
    public InternalReconciliationException() {
    }


    public InternalReconciliationException(String message) {
        super(message);
    }


    public InternalReconciliationException(String message, Throwable cause) {
        super(message, cause);
    }


    public InternalReconciliationException(Throwable cause) {
        super(cause);
    }
}
