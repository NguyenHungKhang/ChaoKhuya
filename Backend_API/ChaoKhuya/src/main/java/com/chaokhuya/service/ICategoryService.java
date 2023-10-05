package com.chaokhuya.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chaokhuya.payload.CategoryDto;

@Service
public interface ICategoryService {
	List<CategoryDto> getAll();
	CategoryDto add(CategoryDto categoryDto);
	CategoryDto update(CategoryDto categoryDto, UUID id);
	void delete(UUID id);
	CategoryDto findById(UUID id);
}
