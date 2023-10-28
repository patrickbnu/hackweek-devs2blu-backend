package com.api.hackweek.models.user;

import ai.pluggy.client.response.InvestorProfile;
import com.api.hackweek.models.account.Account;
import com.api.hackweek.models.account.AccountResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String login;
    private String name;

    @JsonProperty("investor_profile")
    private InvestorProfile investorProfile;

    @JsonProperty("item_id")
    private UUID itemId;

    private AccountResponseDto account;
}
