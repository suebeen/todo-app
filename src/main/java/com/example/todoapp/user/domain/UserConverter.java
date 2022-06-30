package com.example.todoapp.user.domain;

import com.example.todoapp.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.Converter;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public User toEntity(final User user, final UserDTO dto) {
        return User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public UserDTO toUserDTO(final User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
