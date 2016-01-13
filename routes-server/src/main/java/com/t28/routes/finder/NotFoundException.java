package com.t28.routes.finder;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
