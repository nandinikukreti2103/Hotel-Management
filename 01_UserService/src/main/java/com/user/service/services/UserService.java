package com.user.service.services;

import com.user.service.dto.UserDto;
import com.user.service.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUserById(String userId);

    //User updateUser(String userId, User userDetails);

    //TODO Remove Entity from  payload and use  DTO also learn about validation  using  jakarta
    User updateUser(String userId, UserDto userDto);

    void deleteUser(String userId);
}
