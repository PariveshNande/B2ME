package com.b2me.service;

import com.b2me.payload.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    String deleteUser(Integer id);
}
