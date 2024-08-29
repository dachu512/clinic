package com.springapp.clinic.domain.auth.token;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthToken {

    private String authToken;
    private LocalDateTime authTokenExpiredDate;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiredDate;

    public AuthToken(String authToken) {
        this.authToken = authToken;
    }
}
