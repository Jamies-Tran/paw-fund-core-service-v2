package com.paw.fund.core.service.bootstrap.config.handler.exception;

public class PAuthenticationException extends RuntimeException {

    public PAuthenticationException(String message) {
        super(message);
    }

    public PAuthenticationException() {
        super("Xác thực tài khoản thất bại.");
    }
}
