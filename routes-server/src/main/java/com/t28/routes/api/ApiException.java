package com.t28.routes.api;

public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
