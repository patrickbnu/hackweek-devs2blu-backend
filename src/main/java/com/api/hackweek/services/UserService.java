package com.api.hackweek.services;

import com.api.hackweek.exceptions.LoginAlreadyExists;
import com.api.hackweek.models.user.UserRequestDto;
import com.api.hackweek.models.user.UserResponseDto;
import com.api.hackweek.repositories.UserRepository;
import com.api.hackweek.utils.constants.ErrorMessages;
import com.api.hackweek.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USERNAME_NOT_FOUND));
    }

    public UserResponseDto save(UserRequestDto request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new LoginAlreadyExists();
        }

        return mapper.toResponse(
                userRepository.save(mapper.toEntity(request))
        );
    }
}
