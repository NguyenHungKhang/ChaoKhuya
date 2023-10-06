package com.chaokhuya.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chaokhuya.model.Comment;
import com.chaokhuya.payload.CommentDto;
import com.chaokhuya.payload.PageResponse;

public interface ICommentService {
	List<CommentDto> getAll();
	PageResponse<CommentDto> getParentCommentByBlog(UUID blogId, int page, int size);
	PageResponse<CommentDto> getByOwner(UUID ownerId, int page, int size);
	PageResponse<CommentDto> getReplyCommentByComment(UUID commentId, int page, int size);
	CommentDto add(CommentDto commentDto, UUID blogId, UUID ownerId);
	CommentDto reply(CommentDto commentDto, UUID blogId, UUID ownerId, UUID commentId);
	CommentDto update(CommentDto commentDto, UUID id);
	void delete(UUID id);
	CommentDto findById(UUID id);
}
