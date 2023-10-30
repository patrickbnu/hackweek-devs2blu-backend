package com.api.hackweek.utils.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    // JWT Error Messages
    public final String JWT_CREATION_ERROR = "Erro ao gerar o token";
    public final String TOKEN_NOT_FOUND = "O token não foi encontrado";

    // Authentication Error Messages
    public final String INSUFFICIENT_CREDENTIALS = "Credenciais insuficientes";

    // Validation Error Messages
    public final String VALIDATION_ERROR = "Erro de validação";

    // User Error Messages
    public final String USER_NOT_FOUND = "O usuário não foi encontrado";
    public final String USERNAME_NOT_FOUND = "O nome de usuário não foi encontrado";
    public final String LOGIN_ALREADY_EXISTS = "O login já existe";

    // Script Error Messages
    public final String SCRIPT_NOT_FOUND = "O script não foi encontrado";
}
