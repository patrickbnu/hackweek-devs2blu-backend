package com.api.hackweek.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResetPasswordDto {
    @Schema(description = "The token sent to the user email to reset the password")
    @NotNull(message = "O token não pode ser nulo")
    @NotBlank(message = "O token não pode ser vazio")
    private String token;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser vazia")
    private String password;
}
