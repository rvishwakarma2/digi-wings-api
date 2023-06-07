package com.ngo_connect.gen1.exception_handler;

import com.ngo_connect.gen1.models.MessageOnlyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<MessageOnlyResponse> blogNotFoundException(Exception e) {
        return new ResponseEntity(new MessageOnlyResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}