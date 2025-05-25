package com.example.ecommerce.exception;

public class BizCodeException extends Exception {
    private final String errorCode;

    public BizCodeException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static BizCodeException publish(String errorCode) throws BizCodeException {
        throw new BizCodeException(errorCode);
    }
}
