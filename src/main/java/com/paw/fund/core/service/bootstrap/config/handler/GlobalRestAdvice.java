package com.paw.fund.core.service.bootstrap.config.handler;

import com.paw.fund.core.service.bootstrap.config.handler.exception.EErrorCode;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PAccessTokenExpireException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PAuthenticationException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceDuplicateException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotFoundException;
import com.paw.fund.core.service.bootstrap.config.handler.exception.PResourceNotValid;
import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalRestAdvice {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PAuthenticationException.class)
    public PValueResponse<?> authenticationExceptionHandler(PAuthenticationException exc) {
        return PValueResponse.error(
                exc.getMessage(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                EErrorCode.AUTHORIZE_EXCEPTION.getCode()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public PValueResponse<?> methodArgumentExceptionHandler(MethodArgumentNotValidException exc) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError fieldError : exc.getBindingResult().getFieldErrors()) {
            errors.put("message", fieldError.getDefaultMessage());
        }
        return PValueResponse.error(
                errors,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                EErrorCode.RESOURCE_VALIDATE_FAIL.getCode(),
                exc.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PResourceDuplicateException.class)
    public PValueResponse<?> DuplicateExceptionHandler(PResourceDuplicateException exc) {
        return PValueResponse.error(
                String.valueOf(HttpStatus.CONFLICT.value()),
                EErrorCode.RESOURCE_DUPLICATED.getCode(),
                exc.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PResourceNotFoundException.class)
    public PValueResponse<?> NotFoundExceptionHandler(PResourceNotFoundException exc) {
        return PValueResponse.error(
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                EErrorCode.RESOURCE_NOT_FOUND.getCode(),
                exc.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PResourceNotValid.class)
    public PValueResponse<?> NotValidExceptionHandler(PResourceNotValid exc) {
        return PValueResponse.error(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                EErrorCode.REQUEST_NOT_VALID.getCode(),
                exc.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public PValueResponse<?> AccessTokenExceptionHandler(ExpiredJwtException exc) {
        return PValueResponse.error(
                String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                EErrorCode.TOKEN_EXPIRED.getCode(),
                exc.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public PValueResponse<?> internalExceptionHandler(Exception exc) {
        return PValueResponse.error(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                EErrorCode.SERVER_ERROR.getCode(),
                exc.getMessage()
        );
    }
}
