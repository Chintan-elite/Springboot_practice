package com.myapp.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.blog.payloads.APIResponse;
import com.myapp.blog.payloads.UserDto;
import com.myapp.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createdUserDto = userService.addUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userid") int uid)
	{
		UserDto updatedUser = userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable("userid") int id)
	{
		userService.deleteUser(id);
		return new ResponseEntity(new APIResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userid") int id)
	{
		UserDto userdto = userService.getUserById(id);
		return ResponseEntity.ok(userdto);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	
	
	
	
}
