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
import com.chaokhuya.model.Category;
import com.chaokhuya.model.User;
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.repository.IBlogRepository;
import com.chaokhuya.repository.ICategoryRepository;
import com.chaokhuya.repository.IUserRepository;
import com.chaokhuya.service.IBlogService;

@Service
public class BlogService implements IBlogService {

	@Autowired
	private IBlogRepository blogRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	private Blog DtoToBlog(BlogDto blogDto) {
		Blog blog = modelMapper.map(blogDto, Blog.class);
		return blog;
	}

	private BlogDto BlogToDto(Blog blog) {
		BlogDto blogDto = modelMapper.map(blog, BlogDto.class);
		return blogDto;
	}

	@Override
	public List<BlogDto> getAll() {
		List<Blog> blogList = blogRepository.findAll();
		List<BlogDto> blogDtoList = blogList.stream().map(blog -> this.BlogToDto(blog)).collect(Collectors.toList());
		return blogDtoList;
	}

	@Override
	public BlogDto add(BlogDto blogDto, UUID userId, UUID categoryId) {
		User user = userRepository.findById(userId).get();
		Category category = categoryRepository.findById(categoryId).get();
		Blog blog = this.DtoToBlog(blogDto);
		blog.setOwner(user);
		blog.setCategory(category);
		blog.setThumbnail("default");
		Blog newBlog = blogRepository.save(blog);
		return this.BlogToDto(newBlog);
	}

	@Override
	public BlogDto update(BlogDto blogDto, UUID categoryId, UUID id) {
		Blog existBlog = blogRepository.findById(id).get();
		Category category = categoryRepository.findById(categoryId).get();
		existBlog.setCategory(category);
		existBlog.setTitle(blogDto.getTitle());
		existBlog.setText(blogDto.getText());
		Blog updatedBlog = blogRepository.save(existBlog);
		return this.BlogToDto(updatedBlog);
	}

	@Override
	public void delete(UUID id) {
		Blog blog = blogRepository.findById(id).get();
		blogRepository.delete(blog);
	}
	
	@Override
	public BlogDto softDelete(UUID id) {
		Blog existBlog = blogRepository.findById(id).get();
		existBlog.setIsDeleted(true);
		Blog updatedBlog = blogRepository.save(existBlog);
		return this.BlogToDto(updatedBlog);
	}

	@Override
	public BlogDto unSoftDelete(UUID id) {
		Blog existBlog = blogRepository.findById(id).get();
		existBlog.setIsDeleted(false);
		Blog updatedBlog = blogRepository.save(existBlog);
		return this.BlogToDto(updatedBlog);
	}
	
	@Override
	public BlogDto findById(UUID id) {
		Blog blog = blogRepository.findById(id).get();
		return this.BlogToDto(blog);
	}

	@Override
	public BlogDto updateThumbnail(BlogDto blogDto, UUID id) {
		Blog existBlog = blogRepository.findById(id).get();
		existBlog.setThumbnail(blogDto.getThumbnail());
		Blog updatedBlog = blogRepository.save(existBlog);
		return this.BlogToDto(updatedBlog);
	}

	@Override
	public PageResponse<BlogDto> getAll(int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Blog> blogList = blogRepository.findAll(pageable);
		List<BlogDto> blogDtoList = blogList.stream().map((blog) -> this.BlogToDto(blog)).collect(Collectors.toList());
		return new PageResponse<>(blogDtoList, blogList.getNumber(), blogList.getSize(), blogList.getTotalElements(),
				blogList.getTotalPages(), blogList.isLast());
	}

	@Override
	public PageResponse<BlogDto> getByCategory(UUID categoryId, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Blog> blogList = blogRepository.findByCategory(categoryId, pageable);
		List<BlogDto> blogDtoList = blogList.stream().map((blog) -> this.BlogToDto(blog)).collect(Collectors.toList());
		return new PageResponse<>(blogDtoList, blogList.getNumber(), blogList.getSize(), blogList.getTotalElements(),
				blogList.getTotalPages(), blogList.isLast());
	}

	@Override
	public PageResponse<BlogDto> getByOwner(UUID ownerId, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Blog> blogList = blogRepository.findByOwner(ownerId, pageable);
		List<BlogDto> blogDtoList = blogList.stream().map((blog) -> this.BlogToDto(blog)).collect(Collectors.toList());
		return new PageResponse<>(blogDtoList, blogList.getNumber(), blogList.getSize(), blogList.getTotalElements(),
				blogList.getTotalPages(), blogList.isLast());
	}

	@Override
	public PageResponse<BlogDto> getByTitle(String title, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Blog> blogList = blogRepository.findByTitleContaining(title, pageable);
		List<BlogDto> blogDtoList = blogList.stream().map((blog) -> this.BlogToDto(blog)).collect(Collectors.toList());
		return new PageResponse<>(blogDtoList, blogList.getNumber(), blogList.getSize(), blogList.getTotalElements(),
				blogList.getTotalPages(), blogList.isLast());
	}

	

}
