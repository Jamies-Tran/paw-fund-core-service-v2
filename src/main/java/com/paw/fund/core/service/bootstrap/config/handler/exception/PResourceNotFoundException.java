package com.paw.fund.core.service.bootstrap.config.handler.exception;

public class PResourceNotFoundException extends RuntimeException {
    public PResourceNotFoundException(String message) {
        super(message);
    }

    public PResourceNotFoundException() {
        super("Không tìm thấy tài nguyên");
    }
}
