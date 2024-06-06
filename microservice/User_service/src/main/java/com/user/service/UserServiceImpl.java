package com.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public class UserServiceImpl implements UserService{

	List<User> list = List.of(
			
			new User(1120L, "Chintan", "9099613052"),
			new User(1121L, "Kinjal", "9099613052"),
			new User(1122L, "Akshay", "9099613052")
			
			
			);
	
	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
	}

}
