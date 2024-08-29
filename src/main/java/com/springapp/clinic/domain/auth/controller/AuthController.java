package com.springapp.clinic.domain.auth.controller;

import com.springapp.clinic.domain.user.entity.UserDto;
import com.springapp.clinic.domain.auth.entity.LoginRequest;
import com.springapp.clinic.domain.auth.token.AuthToken;
import com.springapp.clinic.domain.auth.service.AuthService;
import com.springapp.clinic.domain.user.service.UserService;
import com.springapp.clinic.utils.VersionNumber;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VersionNumber.VERSION_V1 + "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid LoginRequest loginRequest) {
        AuthToken authToken = authService.login(loginRequest);
        return ResponseEntity.ok(authToken);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto userDtoCreated = userService.createUser(userDto);
        return ResponseEntity.ok("User '" + userDtoCreated.getUsername() + "' registered successfully!");
    }

}
