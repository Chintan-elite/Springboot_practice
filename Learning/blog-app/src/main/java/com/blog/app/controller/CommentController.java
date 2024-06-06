package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payload.CommentDto;
import com.blog.app.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/comment/user/{userid}/post/{postid}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto,
			@PathVariable("userid") Integer userid,
			@PathVariable("postid") Integer postid
			)
	{
		CommentDto createdComment =  this.commentService.createComment(dto, postid, userid);
		return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
		
	}
}
