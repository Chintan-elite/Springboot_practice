package com.smart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.model.User;
import com.smart.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo repo;
	
	@Override
	public void addOrUpdate(User u) {
		
		repo.save(u);
		
	}

	@Override
	public List<User> viewAllUsers() {
		
		return repo.findAll();
	}

	@Override
	public void deleteUser(int id) {
		repo.deleteById(id);
		
	}

	@Override
	public User userBuId(int id) {
		
		User u = repo.findById(id).orElseThrow();
		return u;
	}
	
	

}
