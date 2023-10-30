package com.api.hackweek.exceptions.handlers;

import com.api.hackweek.controllers.PluggyController;
import com.api.hackweek.exceptions.PluggyExecutionException;
import com.api.hackweek.models.error.ErrorDto;
import com.api.hackweek.models.error.FieldErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = PluggyController.class)
public class PluggyExceptionHandler {

    @ExceptionHandler(PluggyExecutionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDto> handlePluggyExecutionException(PluggyExecutionException ex) {
        if (ex.getErrorResponse() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ErrorDto.builder()
                            .message(ex.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                            .build()
            );
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .errors(
                                ex.getErrorResponse().getDetails().stream()
                                        .map(error -> new FieldErrorDto(error.getParameter(), error.getMessage()))
                                        .toList()
                        )
                        .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorDto.builder()
                        .message(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.name())
                        .build()
        );
    }
}
