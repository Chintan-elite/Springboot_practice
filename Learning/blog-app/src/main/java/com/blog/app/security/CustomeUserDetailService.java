package com.blog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.app.entities.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.repo.UserRepo;

@Service
public class CustomeUserDetailService implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	User user =	this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user", "email"+username, 0));
		
	
	return user;
	}

}
