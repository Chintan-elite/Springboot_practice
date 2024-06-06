package com.blog.app.service;

import java.util.List;

import com.blog.app.payload.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto dto);
	public CategoryDto updateCategory(CategoryDto dto, Integer catId);
	public CategoryDto getCategoryById(Integer catId);
	public void deleteCategory(Integer catId);
	public List<CategoryDto> getAllCategory();
	
}
