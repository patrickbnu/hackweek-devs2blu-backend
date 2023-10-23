package com.api.hackweek.exceptions.handlers;

import com.api.hackweek.exceptions.LoginAlreadyExists;
import com.api.hackweek.models.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(LoginAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleLoginAlreadyExists(LoginAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .build()
        );
    }
}
