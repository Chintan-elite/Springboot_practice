package com.blog.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.CategoryDto;
import com.blog.app.repo.CategoryRepo;
import com.blog.app.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		
		Category cat = this.categoryRepo.save(this.modelMapper.map(dto, Category.class));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto dto, Integer catId) {
		
		Category cat = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "id", catId));
		cat.setCategoryTitle(dto.getCategoryTitle());
		cat.setCategoryDescription(dto.getCategoryDescription());
		Category updatedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer catId) {
		Category cat = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "id", catId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category cat = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "id", catId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> list = this.categoryRepo.findAll();
		List<CategoryDto> dtos = list.stream().map(cats->this.modelMapper.map(cats, CategoryDto.class)).collect(Collectors.toList());
		return dtos;
	}

}
