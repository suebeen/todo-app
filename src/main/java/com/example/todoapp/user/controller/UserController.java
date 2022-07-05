package com.example.todoapp.user.controller;

import com.example.todoapp.security.TokenProvider;
import com.example.todoapp.user.dto.request.UserJoinRequestDto;
import com.example.todoapp.user.dto.request.UserLoginRequestDto;
import com.example.todoapp.user.dto.request.UserUpdateRequestDto;
import com.example.todoapp.user.dto.response.UserFindInfoResponseDto;
import com.example.todoapp.user.dto.response.UserLoginResponseDto;
import com.example.todoapp.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Api(value = "UserController")
@RestController
public class UserController {

    private final UserService userService;

    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
            @Valid @RequestBody final UserLoginRequestDto request
    ) throws Throwable {
        final UserLoginResponseDto loginResponse = userService.login(request);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping
    public ResponseEntity<Void> join(
            @Valid @RequestBody final UserJoinRequestDto request
    ) throws Throwable {
        final Long userId = userService.join(request);
        return ResponseEntity.created(URI.create("/api/v1/user/" + userId)).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserFindInfoResponseDto> getUserInfo(
            @PathVariable final String userId
    ) {
        final UserFindInfoResponseDto response = userService.getUserInfo(userId);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUser(
            @AuthenticationPrincipal final String token,
            @Valid @RequestBody final UserUpdateRequestDto request
    ) {
        final String userId = tokenProvider.validateAndGetUserId(token);
        userService.update(userId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal final String token
    ) {
        final String userId = tokenProvider.validateAndGetUserId(token);
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
