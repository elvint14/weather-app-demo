package com.hackerrank.weather.error;

import lombok.Value;

@Value
public class ErrorResponse {

    Integer errorCode;
    String message;

}
