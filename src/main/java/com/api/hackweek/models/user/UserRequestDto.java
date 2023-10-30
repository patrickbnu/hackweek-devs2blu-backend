package com.api.hackweek.models.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres")
    @Pattern(regexp = "^\\D+$", message = "O nome não pode conter números")
    private String name;

    @NotNull(message = "O login não pode ser nulo")
    @NotBlank(message = "O login não pode estar em branco")
    @Size(min = 3, max = 255, message = "O login deve ter entre 3 e 255 caracteres")
    @JsonAlias({"login", "email"})
    private String login;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 3, max = 255, message = "A senha deve ter entre 3 e 255 caracteres")
    private String password;
}
