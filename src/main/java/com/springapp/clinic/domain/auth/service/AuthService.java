package com.springapp.clinic.domain.auth.service;

import com.springapp.clinic.domain.auth.entity.LoginRequest;
import com.springapp.clinic.domain.auth.token.AuthToken;

public interface AuthService {

    AuthToken login(LoginRequest loginRequest);
}
