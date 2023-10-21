package com.api.hackweek.controllers;

import com.api.hackweek.models.auth.LoginRequestDto;
import com.api.hackweek.models.user.UserRequestDto;
import com.api.hackweek.models.user.UserResponseDto;
import com.api.hackweek.services.AuthService;
import com.api.hackweek.services.UserService;
import com.api.hackweek.utils.constants.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDto request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).header(Constants.HEADER_STRING, authService.login(request)).build();
    }

    @PostMapping( "/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }
}
