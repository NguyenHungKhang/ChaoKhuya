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
import com.chaokhuya.model.Reaction;
import com.chaokhuya.model.User;
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.payload.ReactionDto;
import com.chaokhuya.repository.IBlogRepository;
import com.chaokhuya.repository.IReactionRepository;
import com.chaokhuya.repository.IUserRepository;
import com.chaokhuya.service.IReactionService;

@Service
public class ReactionService implements IReactionService {
	
	@Autowired
	private IReactionRepository reactionRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IBlogRepository blogRepository;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	private Reaction DtoToReaction(ReactionDto reactionDto) {
		Reaction reaction = modelMapper.map(reactionDto, Reaction.class);
		return reaction;
	}
	
	private ReactionDto ReactionToDto(Reaction reaction) {
		ReactionDto reactionDto = modelMapper.map(reaction, ReactionDto.class);
		return reactionDto;
	}
	
	@Override
	public List<ReactionDto> getAll() {
		List<Reaction> reactionList = reactionRepository.findAll();
		List<ReactionDto> reactionDtoList = reactionList.stream().map(reaction -> this.ReactionToDto(reaction)).collect(Collectors.toList());
		return reactionDtoList;
	}

	@Override
	public ReactionDto add(UUID ownerId, UUID blogId) {
		User user = userRepository.findById(ownerId).get();
		Blog blog = blogRepository.findById(blogId).get();
		Reaction reaction = new Reaction();
		reaction.setOwner(user);
		reaction.setBlog(blog);
		Reaction newReaction = reactionRepository.save(reaction);
		return this.ReactionToDto(newReaction);
	}
	
	@Override
	public void delete(UUID id) {
		Reaction reaction = reactionRepository.findById(id).get();
		reactionRepository.delete(reaction);
	}

	@Override
	public ReactionDto findById(UUID id) {
		Reaction reaction = reactionRepository.findById(id).get();
		return this.ReactionToDto(reaction);
	}

	@Override
	public PageResponse<ReactionDto> getByBlog(UUID blogId, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Reaction> reactionList = reactionRepository.findByBlog(blogId, pageable);
		List<ReactionDto> reactionDtoList = reactionList.stream().map((reaction) -> this.ReactionToDto(reaction)).collect(Collectors.toList());
		return new PageResponse<>(reactionDtoList, reactionList.getNumber(), reactionList.getSize(), reactionList.getTotalElements(),
				reactionList.getTotalPages(), reactionList.isLast());
	}

	@Override
	public PageResponse<ReactionDto> getByOwner(UUID ownerId, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Reaction> reactionList = reactionRepository.findByOwner(ownerId, pageable);
		List<ReactionDto> reactionDtoList = reactionList.stream().map((reaction) -> this.ReactionToDto(reaction)).collect(Collectors.toList());
		return new PageResponse<>(reactionDtoList, reactionList.getNumber(), reactionList.getSize(), reactionList.getTotalElements(),
				reactionList.getTotalPages(), reactionList.isLast());
	}
	
}
