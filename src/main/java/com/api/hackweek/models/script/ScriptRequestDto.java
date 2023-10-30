package com.api.hackweek.models.script;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScriptRequestDto {
    @Schema(description = "The script itself for the question")
    @NotNull(message = "O script não pode ser nulo")
    @NotBlank(message = "O script não pode estar em branco")
    @Size(min = 3, max = 4000, message = "O script deve ter entre 3 e 4000 caracteres")
    private String script;

    @Schema(description = "The label for the script, this will be used to display the question in the frontend")
    @NotNull(message = "O label não pode ser nulo")
    @NotBlank(message = "O label não pode estar em branco")
    @Size(min = 3, max = 255, message = "O label deve ter entre 3 e 255 caracteres")
    private String label;
}
