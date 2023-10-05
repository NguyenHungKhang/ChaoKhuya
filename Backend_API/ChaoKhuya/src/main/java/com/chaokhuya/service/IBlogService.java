package com.chaokhuya.service;

import java.util.List;
import java.util.UUID;

import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.UserDto;

public interface IBlogService {
	List<BlogDto> getAll();
	PageResponse<BlogDto> getAll(int page, int size);
	PageResponse<BlogDto> getByCategory(UUID categoryId, int page, int size);
	PageResponse<BlogDto> getByOwner(UUID ownerId, int page, int size);
	PageResponse<BlogDto> getByTitle(String title, int page, int size);
	BlogDto add(BlogDto blogDto, UUID userId, UUID categoryId);
	BlogDto update(BlogDto blogDto, UUID categoryId, UUID id);
	BlogDto softDelete(UUID id);
	BlogDto unSoftDelete(UUID id);
	void delete(UUID id);
	BlogDto findById(UUID id);
	BlogDto updateThumbnail(BlogDto blogDto, UUID id);
	
}
