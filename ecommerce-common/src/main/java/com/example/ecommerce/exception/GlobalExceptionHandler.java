package com.example.ecommerce.exception;

import com.example.ecommerce.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BizCodeException.class)
    public Result<Void> handleBizCodeException(BizCodeException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode(), null, locale);
        return Result.custom(ex.getErrorCode(), message, null);
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception ex) {
        return Result.error("system error: " + ex.getMessage());
    }
}
