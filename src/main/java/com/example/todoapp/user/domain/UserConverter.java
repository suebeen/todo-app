package com.example.todoapp.user.domain;

import com.example.todoapp.user.dto.request.UserJoinRequestDto;
import com.example.todoapp.user.dto.response.UserFindInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserConverter {
    private final PasswordEncoder bCryptEncoder;

    public User toEntity(final UserJoinRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(bCryptEncoder.encode(dto.getPassword()))
                .build();
    }

    public UserFindInfoResponseDto toUserFindInfoResponseDto(final User user) {
        return UserFindInfoResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
