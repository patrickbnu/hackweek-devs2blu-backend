package com.api.hackweek.services;

import com.api.hackweek.enums.UserRole;
import com.api.hackweek.exceptions.LoginAlreadyExists;
import com.api.hackweek.models.user.*;
import com.api.hackweek.repositories.UserRepository;
import com.api.hackweek.utils.constants.ErrorMessages;
import com.api.hackweek.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));
    }

    public UserResponseDto save(UserRequestDto request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new LoginAlreadyExists();
        }

        User user = mapper.toEntity(request);

        user.setRole(UserRole.USER);

        return mapper.toResponse(
                userRepository.save(user)
        );
    }

    public UserResponseDto findById(UUID id) {
        return mapper.toResponse(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND)));
    }

    public UserResponseDto updateInvestorProfile(UserInvestorProfileDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));

        user.setInvestorProfile(request.getInvestorProfile());

        return mapper.toResponse(userRepository.save(user));
    }

    public UserResponseDto linkBankAccount(UserAccountSyncDto request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));

        user.setItemId(request.getBankAccountId());

        return mapper.toResponse(userRepository.save(user));
    }
}
