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
import org.springframework.stereotype.Service;

import com.chaokhuya.model.Blog;
import com.chaokhuya.model.Saved;
import com.chaokhuya.model.User;
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.SavedDto;
import com.chaokhuya.repository.IBlogRepository;
import com.chaokhuya.repository.ISavedRepository;
import com.chaokhuya.repository.IUserRepository;
import com.chaokhuya.service.ISavedService;

@Service
public class SavedService implements ISavedService {
	
	@Autowired
	private ISavedRepository savedRepository;
	
	@Autowired
	private IBlogRepository blogRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Saved DtoToSaved(SavedDto savedDto) {
		Saved saved = modelMapper.map(savedDto, Saved.class);
		return saved;
	}
	
	private SavedDto SavedToDto(Saved saved) {
		SavedDto savedDto = modelMapper.map(saved, SavedDto.class);
		return savedDto;
	}

	@Override
	public List<SavedDto> getAll() {
		List<Saved> savedList = savedRepository.findAll();
		List<SavedDto> savedDtoList = savedList.stream().map(saved -> this.SavedToDto(saved)).collect(Collectors.toList());
		return savedDtoList;
	}

	@Override
	public SavedDto add(SavedDto savedDto, UUID ownerId, UUID blogId) {
		User user = userRepository.findById(ownerId).get();
		Blog blog = blogRepository.findById(blogId).get();
		Saved saved = this.DtoToSaved(savedDto);
		saved.setOwner(user);
		saved.setBlog(blog);
		Saved newSaved = savedRepository.save(saved);
		return this.SavedToDto(newSaved);
	}

	@Override
	public void delete(UUID id) {
		Saved saved = savedRepository.findById(id).get();
		savedRepository.delete(saved);
	}

	@Override
	public SavedDto findById(UUID id) {
		Saved saved = savedRepository.findById(id).get();
		return this.SavedToDto(saved);
	}

	@Override
	public PageResponse<SavedDto> getByOwner(UUID ownerId, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Saved> savedList = savedRepository.findByOwner(ownerId, pageable);
		List<SavedDto> savedDtoList = savedList.stream().map((saved) -> this.SavedToDto(saved)).collect(Collectors.toList());
		return new PageResponse<>(savedDtoList, savedList.getNumber(), savedList.getSize(), savedList.getTotalElements(),
				savedList.getTotalPages(), savedList.isLast());
	}
	
	
}
