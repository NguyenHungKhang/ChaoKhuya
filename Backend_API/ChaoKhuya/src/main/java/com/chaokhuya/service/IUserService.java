package com.chaokhuya.service;

import java.util.List;
import java.util.UUID;

import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.UserDto;

public interface IUserService {
	List<UserDto> getAll();
	PageResponse<UserDto> getAll(int page, int size);
	UserDto add(UserDto userDto);
	UserDto update(UserDto userDto, UUID id);
	void delete(UUID id);
	UserDto softDelete(UUID id);
	UserDto unSoftDelete(UUID id);
	UserDto findById(UUID id);
	UserDto enableUser(UUID id);
	UserDto addAdminRole(UUID id);
	void deleteAdminRole(UUID id);
}
