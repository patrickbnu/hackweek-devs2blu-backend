package com.api.hackweek.models.pluggy;

import com.api.hackweek.utils.serializers.DoubleFormatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionsPercentage {
    private String category;

    @JsonSerialize(using = DoubleFormatSerializer.class)
    private double percentage;

    @JsonSerialize(using = DoubleFormatSerializer.class)
    private double totalAmountInCategory;

    private List<TransactionItem> transactions;
}
