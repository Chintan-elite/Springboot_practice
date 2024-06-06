package com.blog.app.service;

import java.util.List;

import com.blog.app.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto dto);
	UserDto updateUser(UserDto dto, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
