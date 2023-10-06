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
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.SavedDto;
import com.chaokhuya.service.ISavedService;
import com.chaokhuya.service.imp.SavedService;

@RestController
@RequestMapping("/saved")
public class SavedController {
	@Autowired
	ISavedService savedService = new SavedService();
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<SavedDto>> getAll(){
		List<SavedDto> savedDtoList = savedService.getAll();
		return new ResponseEntity<List<SavedDto>>(savedDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SavedDto> findById(@PathVariable UUID id){
		SavedDto savedDto = savedService.findById(id);
		return new ResponseEntity<SavedDto>(savedDto,HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/blog/{blogId}")
	public ResponseEntity<SavedDto> add(@RequestBody SavedDto savedDto, @PathVariable UUID userId, @PathVariable UUID blogId){
		SavedDto newSavedDto = savedService.add(savedDto, userId, blogId);
		return new ResponseEntity<SavedDto>(newSavedDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
		savedService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Saved blog deleted Successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/page/user/{ownerId}")
	public ResponseEntity<PageResponse<SavedDto>> getByOwner(
			@RequestParam(value="page", defaultValue = WebConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="size", defaultValue = WebConstants.PAGE_SIZE, required = false) Integer pageSize,
			@PathVariable UUID ownerId
			){
		PageResponse<SavedDto> response = savedService.getByOwner(ownerId, pageNumber, pageSize);
		return new ResponseEntity<PageResponse<SavedDto>>(response, HttpStatus.OK);
	}
}
