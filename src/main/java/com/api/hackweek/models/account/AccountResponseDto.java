package com.api.hackweek.models.account;

import com.api.hackweek.utils.serializers.DoubleFormatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private UUID id;

    @JsonSerialize(using = DoubleFormatSerializer.class)
    private Double income;

    @JsonSerialize(using = DoubleFormatSerializer.class)
    private Double expenses;
}
