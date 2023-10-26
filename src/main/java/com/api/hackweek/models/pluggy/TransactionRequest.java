package com.api.hackweek.models.pluggy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    @Min(value = 2000, message = "O ano deve ser maior ou igual a 2000")
    private Integer year;

    @Min(value = 1, message = "O mês deve ser maior ou igual a 1")
    @Max(value = 12, message = "O mês deve ser menor ou igual a 12")
    private Integer month;
}
