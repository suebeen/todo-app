package com.example.todoapp.user.service;

import com.example.todoapp.common.exception.EntityExceptionSuppliers;
import com.example.todoapp.user.domain.User;
import com.example.todoapp.user.domain.UserConverter;
import com.example.todoapp.user.domain.UserRepository;
import com.example.todoapp.user.dto.request.UserJoinRequestDto;
import com.example.todoapp.user.dto.request.UserUpdateRequestDto;
import com.example.todoapp.user.dto.response.UserFindInfoResponseDto;
import com.example.todoapp.user.dto.response.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    final UserRepository userRepository;
    final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    // login - authenticate
    public UserLoginResponseDto authenticate(final String email, final String password) {
        // jwt 인증 추가 예정
        return null;
    }

    @Transactional
    public String join(final UserJoinRequestDto joinRequest) throws Throwable {
        // exception 처리 : 이미 가입된 이메일 존재
        if (userRepository.existsByEmail(joinRequest.getEmail())) {
            throw (Throwable) EntityExceptionSuppliers.emailAlreadyExist;
        }
        return userRepository.save(userConverter.toEntity(joinRequest))
                .getUserId();
    }

    public User login(final String email, final String password) throws Throwable {
        final User user = userRepository.findByEmail(email).orElseThrow(EntityExceptionSuppliers.userNotFound);
        // user password check
        user.checkPassword(passwordEncoder, password);
        return user;
    }

    @Transactional
    public void update(final String userId, final UserUpdateRequestDto updateDto) {
        final User user = getUser(userId);
        user.update(passwordEncoder, updateDto);
    }

    @Transactional
    public void delete(final String userId) {
        final User user = getUser(userId);
        userRepository.delete(user);
    }

    public UserFindInfoResponseDto getUserInfo(final String userId) {
        final User user = getUser(userId);
        return userConverter.toUserFindInfoResponseDto(user);
    }

    private User getUser(final String userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityExceptionSuppliers.userNotFound);
    }
}
