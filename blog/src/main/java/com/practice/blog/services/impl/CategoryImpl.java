package com.practice.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.blog.entities.Category;
import com.practice.blog.exceptions.ResourceNotFoundException;
import com.practice.blog.payloads.CategoryDto;
import com.practice.blog.payloads.CategoryResponse;
import com.practice.blog.repositories.CategoryRepo;
import com.practice.blog.services.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoruDto) {
		// TODO Auto-generated method stub

		Category cat = this.modelMapper.map(categoruDto, Category.class);

		Category addedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(addedCat, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);

	}

	@Override
	public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		
		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Category> categoryPage = this.categoryRepo.findAll(p);
		List<Category> allCategory = categoryPage.getContent();
		
		
		//List<Category> categories = this.categoryRepo.findAll();

		List<CategoryDto> catDtos = allCategory.stream().map(category -> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		
		CategoryResponse categoryResponse=new CategoryResponse();
		
		categoryResponse.setCatogories(catDtos);
		categoryResponse.setPageNumber(categoryPage.getNumber());
		categoryResponse.setPageSize(categoryPage.getSize());
		categoryResponse.setTotalElements(categoryPage.getTotalElements());
		categoryResponse.setTotalPages(categoryPage.getTotalPages());
		categoryResponse.setLastpage(categoryPage.isLast());

		return categoryResponse;
	}

}
