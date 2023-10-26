package com.api.hackweek.controllers;

import ai.pluggy.client.response.AccountsResponse;
import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.models.pluggy.TransactionsPercentage;
import com.api.hackweek.services.PluggyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pluggy")
@RequiredArgsConstructor
public class PluggyController {
    private final PluggyService pluggyService;

    @GetMapping("/account/{userId}")
    public ResponseEntity<AccountsResponse> getAccounts(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(pluggyService.getAccounts(userId));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<TransactionsPercentage>> getTransactions(@PathVariable UUID userId,
                                                                        @Valid TransactionRequest transactionRequest) {
        return ResponseEntity.ok().body(pluggyService.getTransactions(userId, transactionRequest));
    }
}
