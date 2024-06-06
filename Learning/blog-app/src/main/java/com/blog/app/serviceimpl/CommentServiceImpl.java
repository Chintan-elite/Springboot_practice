package com.blog.app.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Comment;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.CommentDto;
import com.blog.app.repo.CommentRepo;
import com.blog.app.repo.PostRepo;
import com.blog.app.repo.UserRepo;
import com.blog.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto dto, Integer postId, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		
		Comment comment = this.modelMapper.map(dto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		
		Comment createdComment = this.commentRepo.save(comment);
		
		
		return this.modelMapper.map(createdComment, CommentDto.class);
	}

}
