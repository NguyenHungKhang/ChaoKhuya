package com.chaokhuya.service;

import java.util.List;
import java.util.UUID;

import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.SavedDto;

public interface ISavedService {
	List<SavedDto> getAll();
	PageResponse<SavedDto> getByOwner(UUID ownerId, int page, int size);
	SavedDto add(SavedDto savedDto, UUID ownerId, UUID blogId);
	void delete(UUID id);
	SavedDto findById(UUID id);
}
