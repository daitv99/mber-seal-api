package com.smart.exception;

import com.smart.dto.handler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ErrorResponse errorResponse, HttpStatus httpStatus) {
        return ResponseHandler.generateResponse(httpStatus, errorResponse.getMessage(), errorResponse);
    }
}