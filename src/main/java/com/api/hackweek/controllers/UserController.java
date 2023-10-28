package com.api.hackweek.controllers;

import com.api.hackweek.models.user.UserAccountSyncDto;
import com.api.hackweek.models.user.UserInvestorProfileDto;
import com.api.hackweek.models.user.UserResponseDto;
import com.api.hackweek.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/investor-profile")
    public ResponseEntity<UserResponseDto> updateInvestorProfile(@RequestBody @Valid UserInvestorProfileDto request) {
        return ResponseEntity.ok(userService.updateInvestorProfile(request));
    }

    @PatchMapping("/link-bank-account")
    public ResponseEntity<UserResponseDto> linkBankAccount(@RequestBody @Valid UserAccountSyncDto request) {
        return ResponseEntity.ok(userService.linkBankAccount(request));
    }
}
