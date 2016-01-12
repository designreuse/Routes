package com.t28.routes.http;

public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
