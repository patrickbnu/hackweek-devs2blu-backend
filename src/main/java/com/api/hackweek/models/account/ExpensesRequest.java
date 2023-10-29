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
public class ExpensesRequest {
    @Schema(description = "Total expenses of the account")
    @NotNull(message = "As despesas não podem ser nulas")
    private Double expenses;
}
