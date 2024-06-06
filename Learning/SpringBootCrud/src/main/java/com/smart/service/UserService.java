package com.smart.service;

import java.util.List;

import com.smart.model.User;

public interface UserService  {
	
	
	public void addOrUpdate(User u);
	public List<User> viewAllUsers();
	public void deleteUser(int id);
	public User userBuId(int id);

}
