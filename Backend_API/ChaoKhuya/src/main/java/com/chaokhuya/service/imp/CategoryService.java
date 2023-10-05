package com.chaokhuya.service.imp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaokhuya.model.Category;
import com.chaokhuya.payload.CategoryDto;
import com.chaokhuya.repository.ICategoryRepository;
import com.chaokhuya.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Category DtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	private CategoryDto CategoryToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
	
	@Override
	public List<CategoryDto> getAll() {
		List<Category> categoryList = categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> this.CategoryToDto(category)).collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	public CategoryDto add(CategoryDto categoryDto) {
		Category newCategory = categoryRepository.save(this.DtoToCategory(categoryDto));
		return this.CategoryToDto(newCategory);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, UUID id) {
		Category category = categoryRepository.findById(id).get();
		category.setName(categoryDto.getName());
		category.setDesc(categoryDto.getDesc());
		Category updatedCategory = categoryRepository.save(category);
		return this.CategoryToDto(updatedCategory);
	}
	
	@Override
	public void delete(UUID id) {
		Category category = categoryRepository.findById(id).get();
		categoryRepository.delete(category);
	}

	@Override
	public CategoryDto findById(UUID id) {
		return this.CategoryToDto(categoryRepository.findById(id).get());
	}

	

}
