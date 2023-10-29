package com.api.hackweek.controllers;

import com.api.hackweek.models.user.UserAccountSyncDto;
import com.api.hackweek.models.user.UserInvestorProfileDto;
import com.api.hackweek.models.user.UserResetPasswordDto;
import com.api.hackweek.models.user.UserResponseDto;
import com.api.hackweek.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Update user investor profile")
    @PatchMapping("/investor-profile")
    public ResponseEntity<UserResponseDto> updateInvestorProfile(@RequestBody @Valid UserInvestorProfileDto request) {
        return ResponseEntity.ok(userService.updateInvestorProfile(request));
    }

    @Operation(summary = "Link user bank account")
    @PatchMapping("/link-bank-account")
    public ResponseEntity<UserResponseDto> linkBankAccount(@RequestBody @Valid UserAccountSyncDto request) {
        return ResponseEntity.ok(userService.linkBankAccount(request));
    }

    @Operation(summary = "Send reset password email")
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgetPassword(@RequestParam String email) {
        userService.forgotPassword(email);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user password with reset token")
    @PostMapping("/reset-password")
    public ResponseEntity<UserResponseDto> resetPassword(@RequestBody @Valid UserResetPasswordDto request) {
        return ResponseEntity.ok(userService.resetPassword(request));
    }
}
