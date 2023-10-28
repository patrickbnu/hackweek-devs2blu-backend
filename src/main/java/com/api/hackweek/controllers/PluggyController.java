package com.api.hackweek.controllers;

import com.api.hackweek.models.pluggy.TransactionRequest;
import com.api.hackweek.models.pluggy.TransactionsPercentage;
import com.api.hackweek.models.user.User;
import com.api.hackweek.services.PluggyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pluggy")
@RequiredArgsConstructor
public class PluggyController {
    private final PluggyService pluggyService;

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionsPercentage>> getTransactions(@Valid TransactionRequest transactionRequest) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok().body(pluggyService.getTransactions(principal.getId(), transactionRequest));
    }
}
