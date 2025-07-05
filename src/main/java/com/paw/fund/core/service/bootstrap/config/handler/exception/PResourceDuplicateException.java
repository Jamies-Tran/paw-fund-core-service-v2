package com.paw.fund.core.service.bootstrap.config.handler.exception;

public class PResourceDuplicateException extends RuntimeException {
    public PResourceDuplicateException(String message) {
        super(message);
    }

    public PResourceDuplicateException() {
        super("Tài nguyên bị trùng lập");
    }
}
