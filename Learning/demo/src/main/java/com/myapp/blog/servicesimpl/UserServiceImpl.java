package com.myapp.blog.servicesimpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.blog.entity.User;
import com.myapp.blog.exceptions.ResourceNotFoundException;
import com.myapp.blog.payloads.UserDto;
import com.myapp.blog.repositories.UserRepo;
import com.myapp.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDto addUser(UserDto user) {
		
		User u = this.userDtoToUser(user);
		User savedUser = this.userRepo.save(u);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(int id) {
		
		User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User"," Id ",id));
		return this.userToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User"," Id ",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.userToUserDto(updatedUser);

	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> uDtos=users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return uDtos;
	}

	@Override
	public void deleteUser(int id) {
		
		User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User"," Id ",id));
		this.userRepo.delete(user);

	}
	
	public UserDto userToUserDto(User user)
	{
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setAbout(user.getAbout());
		
		return dto;
	}
	
	public User userDtoToUser(UserDto uDto)
	{
		User user = new User();
		user.setId(uDto.getId());
		user.setName(uDto.getName());
		user.setEmail(uDto.getEmail());
		user.setPassword(uDto.getPassword());
		user.setAbout(uDto.getAbout());
		return user;
	}
	

}
