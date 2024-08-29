package com.springapp.clinic.domain.user.mapper;

import com.springapp.clinic.domain.user.entity.UserDto;
import com.springapp.clinic.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
