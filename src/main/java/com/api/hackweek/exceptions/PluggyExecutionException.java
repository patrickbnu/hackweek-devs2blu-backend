package com.api.hackweek.exceptions;

import ai.pluggy.client.response.ErrorResponse;
import lombok.Getter;

@Getter
public class PluggyExecutionException extends RuntimeException {
    private final transient ErrorResponse errorResponse;

    public PluggyExecutionException(String message) {
        super(message);
        this.errorResponse = null;
    }

    public PluggyExecutionException(String message, ErrorResponse errorResponse) {
        super(message);
        this.errorResponse = errorResponse;
    }
}
