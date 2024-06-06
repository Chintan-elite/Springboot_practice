package com.blog.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Comment;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByUser(User user);
	List<Comment> findByPost(Post post);
 }
