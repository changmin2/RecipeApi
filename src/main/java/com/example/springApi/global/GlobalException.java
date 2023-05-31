package com.example.springApi.global;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    // Refresh Token 만료
    @ExceptionHandler(ExpiredJwtException.class)
    public String refreshTokenExpiredException() {
        System.out.println("진입");
        return "해";
    }
}
