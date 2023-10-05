package com.chaokhuya.service;

import java.util.List;
import java.util.UUID;

import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.ReactionDto;

public interface IReactionService {
	List<ReactionDto> getAll();
	PageResponse<ReactionDto> getByBlog(UUID blogId, int page, int size);
	PageResponse<ReactionDto> getByOwner(UUID ownerId, int page, int size);
	ReactionDto add(UUID ownerId, UUID blogId);
	void delete(UUID id);
	ReactionDto findById(UUID id);
}
