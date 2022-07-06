package com.example.todoapp.user.controller;

import com.example.todoapp.security.TokenProvider;
import com.example.todoapp.user.domain.User;
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
    ) {
        final UserLoginResponseDto loginResponse = userService.login(request);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping
    public ResponseEntity<Void> join(
            @Valid @RequestBody final UserJoinRequestDto request
    ) {
        final String userId = userService.join(request);
        return ResponseEntity.created(URI.create("/api/v1/user/" + userId)).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserFindInfoResponseDto> getUserInfo(
            @AuthenticationPrincipal final User user,
            @PathVariable final String userId
    ) {
        final UserFindInfoResponseDto response = userService.getUserInfo(userId);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUser(
            @AuthenticationPrincipal final User user,
            @PathVariable final String userId,
            @Valid @RequestBody final UserUpdateRequestDto request
    ) {
        userService.update(userId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal final User user,
            @PathVariable final String userId
    ) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
