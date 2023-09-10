package com.practice.blog.services;

import java.util.List;

import com.practice.blog.payloads.CategoryDto;
import com.practice.blog.payloads.CategoryResponse;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoruDto);

	
	CategoryDto updateCategory(CategoryDto categoruDto,Integer categoryId);

	
	void deleteCategory(Integer categoryId);

	
	CategoryDto getCategory(Integer categoryId);
	
	CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);

	
}
