package com.chaokhuya.service.imp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaokhuya.model.Blog;
import com.chaokhuya.model.Comment;
import com.chaokhuya.model.User;
import com.chaokhuya.payload.CommentDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.repository.IBlogRepository;
import com.chaokhuya.repository.ICommentRepository;
import com.chaokhuya.repository.IUserRepository;
import com.chaokhuya.service.ICommentService;

@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IBlogRepository blogRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Comment DtoToComment(CommentDto commentDto) {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		return comment;
	}
	
	private CommentDto CommentToDto(Comment comment) {
		CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
		return commentDto;
	}

	@Override
	public List<CommentDto> getAll() {
		List<Comment> commentList = commentRepository.findAll();
		List<CommentDto> commentDtoList = commentList.stream().map(comment -> this.CommentToDto(comment)).collect(Collectors.toList());
		return commentDtoList;
	}

	@Override
	public CommentDto add(CommentDto commentDto, UUID blogId, UUID ownerId) {
		User user = userRepository.findById(ownerId).get();
		Blog blog = blogRepository.findById(blogId).get();
		Comment comment = this.DtoToComment(commentDto);
		comment.setBlog(blog);
		comment.setOwner(user);
		Comment newComment = commentRepository.save(comment);
		return this.CommentToDto(newComment);
	}
	
	@Override
	public CommentDto update(CommentDto commentDto, UUID id) {
		Comment existComment = commentRepository.findById(id).get();
		existComment.setText(commentDto.getText());
		Comment updatedComment = commentRepository.save(existComment);
		return this.CommentToDto(updatedComment);
	}

	@Override
	public void delete(UUID id) {
		Comment comment = commentRepository.findById(id).get();
		commentRepository.delete(comment);
	}

	@Override
	public CommentDto findById(UUID id) {
		Comment comment = commentRepository.findById(id).get();
		return this.CommentToDto(comment);
	}

	

	@Override
	public CommentDto reply(CommentDto commentDto, UUID blogId, UUID ownerId, UUID commentId) {
		User user = userRepository.findById(ownerId).get();
		Blog blog = blogRepository.findById(blogId).get();
		Comment parentComment = commentRepository.findById(commentId).get();
		Comment comment = this.DtoToComment(commentDto);
		comment.setBlog(blog);
		comment.setOwner(user);
		comment.setComment(parentComment);
		Comment newComment = commentRepository.save(comment);
		return this.CommentToDto(newComment);
	}

	@Override
	public PageResponse<CommentDto> getByBlog(UUID blogId, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResponse<CommentDto> getByOwner(UUID blogId, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResponse<CommentDto> getByComment(UUID blogId, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
