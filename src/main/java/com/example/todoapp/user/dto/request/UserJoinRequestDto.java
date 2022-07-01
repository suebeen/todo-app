package com.example.todoapp.user.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserJoinRequestDto {

    @NotBlank(message = "Email must be provided.")
    @Email(message = "Invalid email.")
    private String email;

    @NotBlank(message = "Password must be provided.")
    private String password;

    @NotBlank(message = "UserName must be provided.")
    @Range(min = 0, max = 20, message = "UserName length must within 0 to 20.")
    private String username;

}
