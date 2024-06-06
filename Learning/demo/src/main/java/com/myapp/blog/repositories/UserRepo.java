package com.myapp.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
