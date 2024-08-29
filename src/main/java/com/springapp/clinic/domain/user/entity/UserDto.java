package com.springapp.clinic.domain.user.entity;

import com.springapp.clinic.domain.authorities.Authorities;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;


@Data
public class UserDto {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private boolean enabled;

    private Set<Authorities> authorities;
}
