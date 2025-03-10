package com.blog.app.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;
import com.blog.app.repo.CategoryRepo;
import com.blog.app.repo.PostRepo;
import com.blog.app.repo.UserRepo;
import com.blog.app.service.CategoryService;
import com.blog.app.service.PostService;
import com.blog.app.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer catId, Integer userId) {
		
		
		Category cat = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "Id", catId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		
		Post createdPost =  this.postRepo.save(post);
		return this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		return this.modelMapper.map(this.postRepo.save(post), PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		this.postRepo.delete(post);
		
		
	}

	@Override
	public PostDto postById(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		
		
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy);
		}
		else
		{
			sort = Sort.by(sortBy).descending();
		}
		
		
		Pageable page = PageRequest.of(pageNumber, pageSize,sort);
						
		
		Page<Post> allpost =  this.postRepo.findAll(page);
		System.out.println(allpost);
		List<Post> posts = allpost.getContent();
		
		List<PostDto> postdtos = posts.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(allpost.getNumber());
		postResponse.setPageSize(allpost.getSize());
		postResponse.setTotalElements(allpost.getTotalElements());
		postResponse.setTotalPages(allpost.getTotalPages());
		postResponse.setLastPage(allpost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> postByCategory(Integer catId) {
		
		Category cat = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "Id", catId));
		List<Post> post =  this.postRepo.findByCategory(cat);
		List<PostDto> postdto = post.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postdto;
	}

	@Override
	public List<PostDto> postByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		List<Post> post = this.postRepo.findByUser(user);
		List<PostDto> postdto = post.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postdto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		
		List<Post> posts= this.postRepo.findByTitleContaining(keyword);
		List<PostDto> dtos = posts.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

}
