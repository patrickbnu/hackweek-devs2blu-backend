package com.api.hackweek.services;

import ai.pluggy.client.PluggyClient;
import ai.pluggy.client.request.TransactionsSearchRequest;
import ai.pluggy.client.response.Account;
import ai.pluggy.client.response.Transaction;
import ai.pluggy.client.response.TransactionsResponse;
import com.api.hackweek.exceptions.PluggyExecutionException;
import com.api.hackweek.models.pluggy.TransactionItem;
import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.models.pluggy.TransactionsPercentage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PluggyService {
    private final PluggyClient pluggyClient;
    private final UserService userService;
    private final TranslationService translation;

    public Account getAccount(UUID userId) {
        return executeRequest(pluggyClient.service().getAccounts(userService.findById(userId).getItemId().toString())).getResults().get(0);
    }

    public Double getBalance(UUID userId) {
        return getAccount(userId).getBalance();
    }

    public List<TransactionsPercentage> getTransactions(UUID userId, TransactionRequest transactionRequest) {
        Account account = getAccount(userId);
        int year = transactionRequest.getYear() == null ? LocalDate.now().getYear() : transactionRequest.getYear();
        int month = transactionRequest.getMonth() == null ? LocalDate.now().getMonthValue() : transactionRequest.getMonth();
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);


        return this.mapPluggyTransactionsToListOfTransactionsPercentage(
                executeRequest(pluggyClient.service().getTransactions(
                        account.getId(),
                        new TransactionsSearchRequest()
                                .from(startDate.toString())
                                .to(endDate.toString())
                                .pageSize(100)
                ))
        );
    }

    private Double getTotalAmountInCategory(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    private Double getPercentage(Double totalAmountInCategory, Double totalAmount) {
        return (totalAmountInCategory / totalAmount) * 100;
    }

    private Double getTotalAmount(TransactionsResponse transactions) {
        return transactions.getResults().stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    private <T> T executeRequest(Call<T> call) {
        try {
            Response<T> response = call.execute();

            if (!response.isSuccessful()) {
                throw new PluggyExecutionException(null, pluggyClient.parseError(response));
            }

            return response.body();
        } catch (IOException e) {
            throw new PluggyExecutionException("IO Error: " + e.getMessage());
        } catch (PluggyExecutionException e) {
            throw new PluggyExecutionException("Pluggy Error" + e.getErrorResponse());
        }
    }

    private List<TransactionsPercentage> mapPluggyTransactionsToListOfTransactionsPercentage(TransactionsResponse transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Map<String, List<Transaction>> transactionsByCategory = transaction.getResults().stream()
                .collect(Collectors.groupingBy(Transaction::getCategory));

        return transactionsByCategory.entrySet().stream()
                .map(entry -> {
                    double totalAmountInCategory = getTotalAmountInCategory(entry.getValue());
                    double totalAmount = getTotalAmount(transaction);
                    double percentage = getPercentage(totalAmountInCategory, totalAmount);
                    String translatedCategory = translation.translateToPortuguese(entry.getKey());

                    return TransactionsPercentage.builder()
                            .category(translatedCategory)
                            .percentage(percentage)
                            .totalAmountInCategory(totalAmountInCategory)
                            .transactions(entry.getValue().stream()
                                    .map(transactionResponse2 -> TransactionItem.builder()
                                            .id(UUID.fromString(transactionResponse2.getId()))
                                            .description(transactionResponse2.getDescription())
                                            .amount(transactionResponse2.getAmount())
                                            .date(LocalDateTime.parse(transactionResponse2.getDate(), formatter).toLocalDate())
                                            .category(translatedCategory)
                                            .build())
                                    .toList())
                            .build();
                })
                .toList();
    }
}
