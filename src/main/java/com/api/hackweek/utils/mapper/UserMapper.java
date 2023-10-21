package com.api.hackweek.utils.mapper;

import com.api.hackweek.models.user.User;
import com.api.hackweek.models.user.UserRequestDto;
import com.api.hackweek.models.user.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserRequestDto, UserResponseDto> {

    public UserMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    Class<UserRequestDto> getRequestClass() {
        return UserRequestDto.class;
    }

    @Override
    Class<UserResponseDto> getResponseClass() {
        return UserResponseDto.class;
    }
}
