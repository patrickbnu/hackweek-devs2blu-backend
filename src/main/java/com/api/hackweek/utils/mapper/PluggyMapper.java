package com.api.hackweek.utils.mapper;

import ai.pluggy.client.response.TransactionsResponse;
import com.api.hackweek.models.pluggy.TransactionMonthResponse;
import com.api.hackweek.services.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PluggyMapper {
    private final TranslationService translation;

    public List<TransactionMonthResponse> mapTransactionsToTransactionMonthResponse(TransactionsResponse transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return transaction.getResults().stream()
                .map(transactionResponse -> TransactionMonthResponse.builder()
                        .id(UUID.fromString(transactionResponse.getId()))
                        .description(transactionResponse.getDescriptionRaw())
                        .amount(transactionResponse.getAmount())
                        .date(LocalDateTime.parse(transactionResponse.getDate(), formatter).toLocalDate())
                        .category(translation.translateToPortuguese(transactionResponse.getCategory()))
                        .build())
                .toList();
    }
}
