package com.blog.app.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	@NotBlank
	@Size(min =4 )
	private String categoryTitle;
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
}
