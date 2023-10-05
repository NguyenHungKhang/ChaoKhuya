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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaokhuya.config.WebConstants;
import com.chaokhuya.payload.ApiResponse;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.UserDto;
import com.chaokhuya.service.IUserService;
import com.chaokhuya.service.imp.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService = new UserService();
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll(){
		List<UserDto> userDtoList = userService.getAll();
		return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable UUID id){
		UserDto userDto = userService.findById(id);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDto> add(@RequestBody UserDto userDto){
		UserDto newUserDto = userService.add(userDto);
		return new ResponseEntity<UserDto>(newUserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable UUID id){
		UserDto savedUserDto = userService.update(userDto, id);
		return new ResponseEntity<UserDto>(savedUserDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
		userService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<PageResponse<UserDto>> getAllByPage(
			@RequestParam(value="page", defaultValue = WebConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="size", defaultValue = WebConstants.PAGE_SIZE, required = false) Integer pageSize
			){
		PageResponse<UserDto> response = userService.getAll(pageNumber, pageSize);
		return new ResponseEntity<PageResponse<UserDto>>(response, HttpStatus.OK);
	} 
}
