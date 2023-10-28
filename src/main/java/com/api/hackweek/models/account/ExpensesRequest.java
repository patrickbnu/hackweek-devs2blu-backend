package com.api.hackweek.models.account;

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
    @NotNull(message = "As despesas não podem ser nulas")
    private Double expenses;
}
