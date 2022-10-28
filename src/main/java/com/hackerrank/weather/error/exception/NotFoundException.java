package com.hackerrank.weather.error.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class NotFoundException extends RuntimeException {

    private final Integer errorCode;
    private final String message;

    public NotFoundException(String message) {
        super(message);
        this.errorCode = 404;
        this.message = message;
    }

    public static NotFoundException of(String message, Object... args) {
        return new NotFoundException(MessageFormat.format(message, args));
    }

}
