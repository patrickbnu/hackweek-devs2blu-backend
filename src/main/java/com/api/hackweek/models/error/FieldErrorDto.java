package com.api.hackweek.models.error;

import io.swagger.v3.oas.annotations.media.Schema;

public record FieldErrorDto(
        @Schema(description = "Field name", example = "name")
        String field,

        @Schema(description = "Error message", example = "Name is required")
        String message
) {
}
