package com.api.hackweek.utils.mapper;

import com.api.hackweek.models.account.Account;
import com.api.hackweek.models.account.AccountBalanceDto;
import com.api.hackweek.models.account.AccountResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountBalanceDto toBalanceDto(Account account, Double balance) {
        return AccountBalanceDto.builder()
                .balance(balance)
                .income(account.getIncome())
                .expenses(account.getExpenses())
                .build();
    }

    public AccountResponseDto toResponse(Account account) {
        return AccountResponseDto.builder()
                .id(account.getId())
                .income(account.getIncome())
                .expenses(account.getExpenses())
                .build();
    }
}
