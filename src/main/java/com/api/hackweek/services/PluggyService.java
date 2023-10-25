package com.api.hackweek.services;

import ai.pluggy.client.PluggyClient;
import ai.pluggy.client.request.TransactionsSearchRequest;
import ai.pluggy.client.response.AccountsResponse;
import ai.pluggy.client.response.ConnectorsResponse;
import ai.pluggy.client.response.TransactionsResponse;
import com.api.hackweek.exceptions.PluggyExecutionException;
import com.api.hackweek.models.pluggy.TransactionMonthResponse;
import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.utils.mapper.PluggyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PluggyService {
    private final PluggyClient pluggyClient;
    private final PluggyMapper pluggyMapper;

    public ConnectorsResponse getConnectors() {
        return executeRequest(pluggyClient.service().getConnectors());
    }

    public AccountsResponse getAccount(UUID itemId) {
        return executeRequest(pluggyClient.service().getAccounts(itemId.toString()));
    }

    public TransactionsResponse getTransactions(UUID accountId, TransactionsSearchRequest searchRequest) {
        return executeRequest(pluggyClient.service().getTransactions(accountId.toString(), searchRequest));
    }

    public List<TransactionMonthResponse> getTransactions(UUID accountId, TransactionRequest transactionRequest) {
        int year = transactionRequest.getYear() == null ? LocalDate.now().getYear() : transactionRequest.getYear();
        LocalDate startDate = LocalDate.of(year, transactionRequest.getMonth(), 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        return pluggyMapper.mapTransactionsToTransactionMonthResponse(
                executeRequest(pluggyClient.service().getTransactions(
                        accountId.toString(),
                        new TransactionsSearchRequest()
                                .from(startDate.toString())
                                .to(endDate.toString())
                                .pageSize(100)
                )));
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
}
