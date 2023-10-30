package com.api.hackweek.services;

import com.api.hackweek.models.auth.LoginRequestDto;
import com.api.hackweek.models.user.User;
import com.api.hackweek.utils.JwtUtils;
import com.api.hackweek.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public String login(LoginRequestDto loginRequestDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getLogin(), loginRequestDto.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        return jwtUtils.generateToken((User) authentication.getPrincipal());
    }
}
