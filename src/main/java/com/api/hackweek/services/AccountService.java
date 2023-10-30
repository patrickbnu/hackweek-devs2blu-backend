package com.api.hackweek.services;

import com.api.hackweek.models.account.Account;
import com.api.hackweek.models.account.AccountBalanceDto;
import com.api.hackweek.models.account.AccountResponseDto;
import com.api.hackweek.repositories.AccountRepository;
import com.api.hackweek.utils.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper mapper;
    private final PluggyService pluggyService;

    public AccountBalanceDto findByUserId(UUID userId) {
        return mapper.toBalanceDto(
                accountRepository.findByUserId(userId),
                pluggyService.getAccount(userId).getBalance()
        );
    }

    public AccountResponseDto updateIncome(UUID userId, Double income) {
        Account account = accountRepository.findByUserId(userId);

        account.setIncome(income);

        return mapper.toResponse(
                accountRepository.save(account)
        );
    }

    public AccountResponseDto updateExpenses(UUID userId, Double expenses) {
        Account account = accountRepository.findByUserId(userId);

        account.setExpenses(expenses);

        return mapper.toResponse(
                accountRepository.save(account)
        );
    }
}
