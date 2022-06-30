package com.example.todoapp.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Token must not be blank.")
    private String token;

    @NotBlank(message = "User id must not be blank.")
    private String userId;

    @NotBlank(message = "Email must be provided.")
    @Email(message = "Invalid email.")
    private String email;

    @NotBlank(message = "Username must not be blank.")
    private String username;

    @NotBlank(message = "Password must not be blank.")
    private String password;
}
