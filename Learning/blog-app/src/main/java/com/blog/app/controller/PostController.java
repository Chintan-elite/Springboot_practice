package com.blog.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.config.AppConstants;
import com.blog.app.payload.ApiResponse;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;
import com.blog.app.service.FileService;
import com.blog.app.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	FileService fileService;
	
	@Value("${project.image}")
	String path;
	
	@PostMapping("/category/{catId}/user/{userId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable("catId") Integer cid, @PathVariable("userId") Integer uid)
	{
		PostDto postDto = postService.createPost(dto, cid, uid);
		return new ResponseEntity(postDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> postByCat(@PathVariable("catId") Integer cid)
	{
		List<PostDto> allpost = this.postService.postByCategory(cid);
		return new ResponseEntity<List<PostDto>>(allpost,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> postByUser(@PathVariable("userId") Integer uid)
	{
		List<PostDto> allpost = this.postService.postByUser(uid);
		return new ResponseEntity<List<PostDto>>(allpost,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> allPost(
			@RequestParam(value = "pageNumber", defaultValue =AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
			
			)
	{
		PostResponse dto= this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{pid}")
	public ResponseEntity<PostDto> postById(@PathVariable("pid") Integer pid)
	{
		PostDto dto = this.postService.postById(pid);
		return new ResponseEntity<PostDto>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{pid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("pid") Integer pid)
	{
		this.postService.deletePost(pid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully !!!", true),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{pid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable("pid") Integer pid)
	{
		PostDto upsatedPost = this.postService.updatePost(dto, pid);
		return new ResponseEntity<PostDto>(upsatedPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword)
	{
		List<PostDto> posts = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable("postId") Integer postId) throws IOException
	{
		
		PostDto dto = this.postService.postById(postId);
		String filename = this.fileService.uploadImage(path, image);
		System.out.println(filename);
		dto.setImageName(filename);
		PostDto updatedPost =  this.postService.updatePost(dto, postId);
		System.out.println(updatedPost);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@GetMapping(value="/posts/image/{imgName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downLoadImage(@PathVariable("imgName") String imgName,HttpServletResponse response) throws IOException
	{
		InputStream resource = this.fileService.getResource(path, imgName);	
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
	
}
