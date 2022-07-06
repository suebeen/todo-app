package com.example.todoapp.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {

    @NotBlank(message = "UserName must be provided.")
    private String username;

    @NotBlank(message = "Password must be provided.")
    private String password;

}
