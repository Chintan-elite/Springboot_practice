package com.blog.app.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.app.entities.Category;
import com.blog.app.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private List<CommentDto> comments = new ArrayList<>();
}
