package com.practice.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.practice.blog.payloads.APIResponse;
import com.practice.blog.payloads.CategoryDto;
import com.practice.blog.payloads.CategoryResponse;
import com.practice.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	 create

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	
//	update

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categotyDto,@PathVariable Integer categoryId){
		
		CategoryDto updatedCategory = this.categoryService.updateCategory(categotyDto, categoryId);
		
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	
//	delete

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<APIResponse> deleteCetegory(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<APIResponse>(new APIResponse("Category deleted",true),HttpStatus.OK);
		
	}
	
	
	
	
	
//	get
	
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		
		CategoryDto category = this.categoryService.getCategory(categoryId);
		
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
		
	}
	

//	getAll

	@GetMapping("/")
	public ResponseEntity<CategoryResponse> getCategories(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="2",required=false) Integer pageSize
			){
		
		CategoryResponse categoryResponse = this.categoryService.getAllCategories(pageNumber,pageSize);
		
//		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
		
		return ResponseEntity.ok(categoryResponse);
		
	}
	
}
