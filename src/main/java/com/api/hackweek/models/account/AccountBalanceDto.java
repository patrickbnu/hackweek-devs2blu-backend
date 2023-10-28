package com.api.hackweek.models.account;

import com.api.hackweek.utils.serializers.DoubleFormatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountBalanceDto {
    private Double balance;

    @JsonSerialize(using = DoubleFormatSerializer.class)
    private Double income;

    @JsonSerialize(using = DoubleFormatSerializer.class)
    private Double expenses;
}
