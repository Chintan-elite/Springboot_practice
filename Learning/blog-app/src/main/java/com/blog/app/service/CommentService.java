package com.blog.app.service;

import com.blog.app.payload.CommentDto;

public interface CommentService {
		
		
	public CommentDto createComment(CommentDto dto, Integer postId, Integer userId);
	
}
