package com.example.todoapp.user.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {

    @NotBlank(message = "UserName must be provided.")
    @Range(min = 0, max = 20, message = "UserName length must within 0 to 20.")
    private String username;

    @NotBlank(message = "Password must be provided.")
    private String password;

}
