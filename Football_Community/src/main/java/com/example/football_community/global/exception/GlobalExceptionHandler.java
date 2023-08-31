package com.example.football_community.global.exception;

import com.example.football_community.global.message.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = { GlobalException.class })
    protected ResponseEntity handleCustomException(GlobalException e) {
        return ResponseMessage.ErrorResponse(e.getErrorCode());
    }
}
