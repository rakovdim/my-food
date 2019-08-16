package com.myfood.commons.service;

public class InconsistentFlowOperationException extends RuntimeException {
    public InconsistentFlowOperationException() {
    }

    public InconsistentFlowOperationException(String message) {
        super(message);
    }

    public InconsistentFlowOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistentFlowOperationException(Throwable cause) {
        super(cause);
    }
}
