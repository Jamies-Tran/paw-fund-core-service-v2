package com.paw.fund.core.service.bootstrap.config.handler.exception;

public class PResourceNotValid extends RuntimeException {
    public PResourceNotValid(String message) {
        super(message);
    }

    public PResourceNotValid() {
        super("Tài nguyên không khả dụng");
    }
}
