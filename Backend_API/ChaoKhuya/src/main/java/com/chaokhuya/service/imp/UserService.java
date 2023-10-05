package com.chaokhuya.service.imp;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.model.AbstractPersistentProperty;
import org.springframework.stereotype.Service;

import com.chaokhuya.config.WebConstants;
import com.chaokhuya.model.Blog;
import com.chaokhuya.model.Role;
import com.chaokhuya.model.User;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.UserDto;
import com.chaokhuya.repository.IRoleRepository;
import com.chaokhuya.repository.IUserRepository;
import com.chaokhuya.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private User DtoToUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto UserToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
	@Override
	public List<UserDto> getAll() {
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = userList.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public UserDto add(UserDto userDto) {
		User newUser = this.DtoToUser(userDto);
		Role role = roleRepository.findById(WebConstants.USER).get();
		newUser.getRoles().add(role);
		User addedUser = userRepository.save(this.DtoToUser(userDto));
		return this.UserToDto(addedUser);
	}

	@Override
	public UserDto update(UserDto userDto, UUID id) {
		User existUser = userRepository.findById(id).get();
		existUser.setEmail(userDto.getEmail());
		existUser.setPassword(userDto.getPassword());
		existUser.setLastname(userDto.getLastname());
		existUser.setFirstname(userDto.getFirstname());
		existUser.setBio(userDto.getBio());
		User updatedUser = userRepository.save(existUser);
		return this.UserToDto(updatedUser);
	}

	@Override
	public void delete(UUID id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);
	}
	
	@Override
	public UserDto softDelete(UUID id) {
		User existUser = userRepository.findById(id).get();
		existUser.setIsDeleted(true);
		User updatedUser = userRepository.save(existUser);
		return this.UserToDto(updatedUser);
	}

	@Override
	public UserDto unSoftDelete(UUID id) {
		User existUser = userRepository.findById(id).get();
		existUser.setIsDeleted(false);
		User updatedUser = userRepository.save(existUser);
		return this.UserToDto(updatedUser);
	}

	@Override
	public UserDto findById(UUID id) {
		return this.UserToDto(userRepository.findById(id).get());
	}

	@Override
	public PageResponse<UserDto> getAll(int page, int size) {
		Sort sort = Sort.by("createdTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<User> userList = userRepository.findAll(pageable);
		List<UserDto> userDtoList = userList.stream().map((user) -> this.UserToDto(user)).collect(Collectors.toList());
		return new PageResponse<>(userDtoList, userList.getNumber(), userList.getSize(), userList.getTotalElements(), userList.getTotalPages(), userList.isLast());
		
	}

	@Override
	public UserDto addAdminRole(UUID id) {
		User user = userRepository.findById(id).get();
		Role role = roleRepository.findById(WebConstants.ADMIN).get();
		user.getRoles().add(role);
		User updatedUser = userRepository.save(user);
		return this.UserToDto(updatedUser);
	}

	@Override
	public void deleteAdminRole(UUID id) {
		User user = userRepository.findById(id).get();
		Role role = roleRepository.findById(WebConstants.ADMIN).get();
		user.getRoles().remove(role);
		userRepository.save(user);
	}

	@Override
	public UserDto enableUser(UUID id) {
		User existUser = userRepository.findById(id).get();
		existUser.setIsEnable(true);
		User updatedUser = userRepository.save(existUser);
		return this.UserToDto(updatedUser);
	}

	


}
