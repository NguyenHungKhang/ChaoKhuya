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
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.CommentDto;
import com.chaokhuya.service.ICommentService;
import com.chaokhuya.service.imp.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	ICommentService commentService = new CommentService();
	
	@GetMapping("/")
	public ResponseEntity<List<CommentDto>> getAll() {
		List<CommentDto> commentDtoList = commentService.getAll();
		return new ResponseEntity<List<CommentDto>>(commentDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CommentDto> findById(@PathVariable UUID id) {
		CommentDto commentDto = commentService.findById(id);
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/blog/{blogId}")
	public ResponseEntity<CommentDto> add(@RequestBody CommentDto commentDto, @PathVariable UUID userId, @PathVariable UUID blogId) {
		CommentDto newCommentDto = commentService.add(commentDto, blogId, userId);
		return new ResponseEntity<CommentDto>(newCommentDto, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/blog/{blogId}/comment/{commentId}")
	public ResponseEntity<CommentDto> reply(@RequestBody CommentDto commentDto, @PathVariable UUID userId, @PathVariable UUID blogId, @PathVariable UUID commentId) {
		CommentDto newCommentDto = commentService.reply(commentDto, blogId, userId, commentId);
		return new ResponseEntity<CommentDto>(newCommentDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto, @PathVariable UUID id) {
		CommentDto updateCommentDto = commentService.update(commentDto, id);
		return new ResponseEntity<CommentDto>(updateCommentDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
		commentService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully",true),HttpStatus.OK);
	}
}
