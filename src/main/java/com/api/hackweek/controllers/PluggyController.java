package com.api.hackweek.controllers;

import ai.pluggy.client.request.TransactionsSearchRequest;
import ai.pluggy.client.response.AccountsResponse;
import ai.pluggy.client.response.ConnectorsResponse;
import ai.pluggy.client.response.TransactionsResponse;
import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.services.PluggyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pluggy")
@RequiredArgsConstructor
public class PluggyController {
    private final PluggyService pluggyService;

    @GetMapping("/connectors")
    public ResponseEntity<ConnectorsResponse> getConnectors() {
        return ResponseEntity.ok().body(pluggyService.getConnectors());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountsResponse> getAccount(@PathVariable UUID id) {
        return ResponseEntity.ok().body(pluggyService.getAccount(id));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionsResponse> getTransactions(@PathVariable UUID id,
                                                                TransactionsSearchRequest searchRequest) {
        return ResponseEntity.ok().body(pluggyService.getTransactions(id, searchRequest));
    }

    @GetMapping("/transactions/{id}/search")
    public ResponseEntity<TransactionsResponse> getTransactions(@PathVariable UUID id,
                                                                @Valid TransactionRequest transactionRequest) {
        return ResponseEntity.ok().body(pluggyService.getTransactions(id, transactionRequest));
    }
}
