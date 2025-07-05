package com.paw.fund.core.service.bootstrap.config.handler.exception;

public class PAccessTokenExpireException extends RuntimeException{
    public PAccessTokenExpireException(String message) {
        super(message);
    }

    public PAccessTokenExpireException() {
        super("Phiên đăng nhập đã hết hạn.");
    }
}
