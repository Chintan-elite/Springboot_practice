package com.blog.app.service;

import java.util.List;

import com.blog.app.entities.Post;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer catId, Integer userId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto postById(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	List<PostDto> postByCategory(Integer catId);
	
	List<PostDto> postByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);
}
