package com.example.todoapp.user.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserLoginRequestDto {

    @NotBlank(message = "Email must be provided.")
    @Email(message = "Invalid email.")
    private String email;

    @NotBlank(message = "Password must be provided.")
    private String password;

}
