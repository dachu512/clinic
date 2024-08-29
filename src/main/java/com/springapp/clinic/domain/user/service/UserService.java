package com.springapp.clinic.domain.user.service;

import com.springapp.clinic.domain.user.entity.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    boolean existsByUsername(String username);
}
