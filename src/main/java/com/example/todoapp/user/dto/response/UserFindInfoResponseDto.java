package com.example.todoapp.user.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class UserFindInfoResponseDto {

    private String userId;

    private String email;

    private String username;

}
