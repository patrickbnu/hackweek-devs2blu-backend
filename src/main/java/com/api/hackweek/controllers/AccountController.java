package com.api.hackweek.controllers;

import com.api.hackweek.models.account.AccountBalanceDto;
import com.api.hackweek.models.account.AccountResponseDto;
import com.api.hackweek.models.account.ExpensesRequest;
import com.api.hackweek.models.account.IncomeRequest;
import com.api.hackweek.models.user.User;
import com.api.hackweek.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Account")
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Get account balance, income and expenses by user authenticated")
    @GetMapping
    public ResponseEntity<AccountBalanceDto> getAccountBalance() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(accountService.findByUserId(user.getId()));
    }

    @Operation(summary = "Update account income by user authenticated")
    @PatchMapping("/income")
    public ResponseEntity<AccountResponseDto> updateIncome(@RequestBody @Valid IncomeRequest income) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(accountService.updateIncome(user.getId(), income.getIncome()));
    }

    @Operation(summary = "Update account expenses by user authenticated")
    @PatchMapping("/expenses")
    public ResponseEntity<AccountResponseDto> updateExpenses(@RequestBody @Valid ExpensesRequest expenses) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(accountService.updateExpenses(user.getId(), expenses.getExpenses()));
    }

}
