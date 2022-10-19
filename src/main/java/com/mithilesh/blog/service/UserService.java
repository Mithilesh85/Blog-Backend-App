package com.mithilesh.blog.service;

import com.mithilesh.blog.payload.UserDto;

import java.util.List;

public interface UserService {
 UserDto registerNewUser(UserDto user);
 UserDto createUser(UserDto user);
 UserDto updateUser(UserDto user, int userId);
 List<UserDto> getAllUsers();
 UserDto getUserById(int userId);
 void deleteUser(int userId);
}
