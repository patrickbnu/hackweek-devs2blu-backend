package com.api.hackweek.models.user;

import ai.pluggy.client.response.InvestorProfile;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserInvestorProfileDto {
    @Schema(description = "The investor profile to be set to the user, this will be used in the IA to recommend the best investment options")
    @NotNull(message = "O perfil do investidor não pode ser nulo")
    @JsonProperty("investor_profile")
    private InvestorProfile investorProfile;

    @NotNull(message = "O id do usuário não pode ser nulo")
    @JsonAlias({"user_id", "id", "user"})
    private UUID userId;
}
