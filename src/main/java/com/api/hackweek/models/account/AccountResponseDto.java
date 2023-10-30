package com.api.hackweek.models.account;

import com.api.hackweek.utils.serializers.DoubleFormatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponseDto {
    private UUID id;

    @Schema(description = "Total income of the account based on transactions")
    @JsonSerialize(using = DoubleFormatSerializer.class)
    private Double income;

    @Schema(description = "Total expenses of the account based on transactions")
    @JsonSerialize(using = DoubleFormatSerializer.class)
    private Double expenses;
}
