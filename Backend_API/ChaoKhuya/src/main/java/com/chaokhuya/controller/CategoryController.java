package com.chaokhuya.controller;

import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.chaokhuya.payload.ApiResponse;
import com.chaokhuya.payload.CategoryDto;
import com.chaokhuya.service.ICategoryService;
import com.chaokhuya.service.imp.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService = new CategoryService();
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAll(){
		List<CategoryDto> categoryDtoList = categoryService.getAll();
		return new ResponseEntity<List<CategoryDto>>(categoryDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> findById(@PathVariable UUID id){
		CategoryDto categoryDto = categoryService.findById(id);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> add(@RequestBody CategoryDto categoryDto){
		CategoryDto savedCategoryDto = categoryService.add(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategoryDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto, @PathVariable UUID id){
		CategoryDto savedCategoryDto = categoryService.update(categoryDto, id);
		return new ResponseEntity<CategoryDto>(savedCategoryDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
		categoryService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true),HttpStatus.OK);
	}
}
