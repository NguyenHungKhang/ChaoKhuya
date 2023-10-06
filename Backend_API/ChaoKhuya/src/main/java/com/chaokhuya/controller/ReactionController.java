package com.chaokhuya.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaokhuya.config.WebConstants;
import com.chaokhuya.payload.ApiResponse;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.ReactionDto;
import com.chaokhuya.payload.SavedDto;
import com.chaokhuya.service.IReactionService;
import com.chaokhuya.service.imp.ReactionService;

@RestController
@RequestMapping("/reaction")
public class ReactionController {
	
	@Autowired
	IReactionService reactionService = new ReactionService();
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<ReactionDto>> getAll(){
		List<ReactionDto> reactionDtoList = reactionService.getAll();
		return new ResponseEntity<List<ReactionDto>>(reactionDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReactionDto> findById(@PathVariable UUID id){
		ReactionDto reactionDto = reactionService.findById(id);
		return new ResponseEntity<ReactionDto>(reactionDto, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/blog/{blogId}")
	public ResponseEntity<ReactionDto> add(@RequestBody ReactionDto reactionDto, @PathVariable UUID userId, @PathVariable UUID blogId){
		ReactionDto newReactionDto = reactionService.add(userId, blogId);
		return new ResponseEntity<ReactionDto>(newReactionDto, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
		reactionService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Reaction deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/page/user/{ownerId}")
	public ResponseEntity<PageResponse<ReactionDto>> getByOwner(
			@RequestParam(value="page", defaultValue = WebConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="size", defaultValue = WebConstants.PAGE_SIZE, required = false) Integer pageSize,
			@PathVariable UUID ownerId
			){
		PageResponse<ReactionDto> response = reactionService.getByOwner(ownerId, pageNumber, pageSize);
		return new ResponseEntity<PageResponse<ReactionDto>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/page/blog/{blogId}")
	public ResponseEntity<PageResponse<ReactionDto>> getByBlog(
			@RequestParam(value="page", defaultValue = WebConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="size", defaultValue = WebConstants.PAGE_SIZE, required = false) Integer pageSize,
			@PathVariable UUID blogId
			){
		PageResponse<ReactionDto> response = reactionService.getByBlog(blogId, pageNumber, pageSize);
		return new ResponseEntity<PageResponse<ReactionDto>>(response, HttpStatus.OK);
	}
}
	
