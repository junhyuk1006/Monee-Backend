package com.example.moneebackend.dto.user;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final String token;
    private final String email;
    private final String nickname;

    public UserLoginResponseDto(String token, String email, String nickname){
        this.token = token;
        this.email = email;
        this.nickname = nickname;
    }
}
