package com.springapp.clinic.domain.user.service;

import com.springapp.clinic.config.exceptions.UsernameAlreadyExistsException;
import com.springapp.clinic.domain.user.mapper.UserMapper;
import com.springapp.clinic.domain.user.entity.UserDto;
import com.springapp.clinic.domain.user.entity.User;
import com.springapp.clinic.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.existsByUsername(userDto.getUsername())){
            throw new UsernameAlreadyExistsException("Username is already taken");
        }

        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public boolean existsByUsername(String username) {
        //TODO:
        return false;
    }
}
