package com.api.hackweek.exceptions;

import com.api.hackweek.utils.constants.ErrorMessages;

public class LoginAlreadyExists extends RuntimeException {
    public LoginAlreadyExists(String message) {
        super(message);
    }

    public LoginAlreadyExists() {
        super(ErrorMessages.LOGIN_ALREADY_EXISTS);
    }
}
