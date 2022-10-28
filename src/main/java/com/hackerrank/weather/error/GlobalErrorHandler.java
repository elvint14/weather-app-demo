package com.hackerrank.weather.error;

import com.hackerrank.weather.error.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleDataNotFoundException(NotFoundException ex) {
        final Integer errorCode = ex.getErrorCode();
        final String message = ex.getMessage();
        log.error("[Error] | Status: {} | Code: {} | Message: {}", NOT_FOUND, errorCode, message);
        return new ErrorResponse(errorCode, message);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAll(Exception ex) {
        final String message = ex.getMessage();
        log.error("[Error] | Status: {} | Code: {} | Message: {}", INTERNAL_SERVER_ERROR, 500, message, ex);
        return new ErrorResponse(500, "Unexpected Internal Server Error Happened!");
    }

}
