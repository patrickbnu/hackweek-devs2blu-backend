package com.api.hackweek.models.user;

import ai.pluggy.client.response.InvestorProfile;
import com.api.hackweek.models.account.Account;
import com.api.hackweek.models.account.AccountResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "The investor profile to be set to the user, this will be used in the IA to recommend the best investment options")
    @JsonProperty("investor_profile")
    private InvestorProfile investorProfile;

    @Schema(description = "The id of the bank account to be synced, you can get it from the itemId provided from the pluggy widget")
    @JsonProperty("item_id")
    private UUID itemId;

    private AccountResponseDto account;
}
