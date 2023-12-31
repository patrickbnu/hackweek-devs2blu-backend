package com.api.hackweek.models.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountSyncDto {
    @JsonAlias({"user_id", "id"})
    @NotNull(message = "O id do usuário é obrigatório")
    private UUID userId;

    @Schema(description = "The id of the bank account to be synced, you can get it from the itemId provided from the pluggy widget")
    @JsonAlias({"bank_account_id", "item_id"})
    @NotNull(message = "O id da conta bancária é obrigatório")
    private UUID bankAccountId;
}
