package com.api.hackweek.models.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorDto {
    @Schema(description = "Error message", example = "Validation failed")
    private final String message;

    @Schema(description = "Error status", example = "400")
    private final String status;

    private final List<FieldErrorDto> errors;
}
