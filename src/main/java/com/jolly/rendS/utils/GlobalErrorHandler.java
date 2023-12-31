package com.jolly.rendS.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> notFoundExceptionHandler(BaseException exception) {
        return new ResponseEntity<>(new StandardErrorResponse(exception), exception.getStatusCode());
    }
}