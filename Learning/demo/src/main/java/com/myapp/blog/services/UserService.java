package com.myapp.blog.services;

import java.util.List;

import com.myapp.blog.payloads.UserDto;

public interface UserService {
	
		UserDto addUser(UserDto user);
		UserDto getUserById(int id);
		UserDto updateUser(UserDto user, int id);
		List<UserDto> getAllUser();
		void deleteUser(int id);
}
