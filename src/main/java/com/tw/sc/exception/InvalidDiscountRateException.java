package com.tw.sc.exception;

public class InvalidDiscountRateException extends RuntimeException {
    public InvalidDiscountRateException(String msg) {
        super(msg);
    }
}
