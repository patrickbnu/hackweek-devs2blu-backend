package com.api.hackweek.models.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequest {
    @Schema(description = "Total income of the account")
    @NotNull(message = "A renda não pode ser nula")
    private Double income;
}
