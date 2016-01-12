package com.t28.routes.http;

public class HttpException extends Exception {
    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }
}
