package com.chaokhuya.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chaokhuya.config.WebConstants;
import com.chaokhuya.payload.ApiResponse;
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.repository.IImageRepository;
import com.chaokhuya.service.IBlogService;
import com.chaokhuya.service.ICommonImageService;
import com.chaokhuya.service.imp.BlogService;
import com.chaokhuya.service.imp.CommonImageService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	IBlogService blogService = new BlogService();

	@Autowired
	private IImageRepository imageRepository;

	@Autowired
	ICommonImageService commonImageService = new CommonImageService();

	@GetMapping("/")
	public ResponseEntity<List<BlogDto>> getAll() {
		List<BlogDto> blogDtoList = blogService.getAll();
		return new ResponseEntity<List<BlogDto>>(blogDtoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BlogDto> findById(@PathVariable UUID id) {
		BlogDto blogDto = blogService.findById(id);
		return new ResponseEntity<BlogDto>(blogDto, HttpStatus.OK);
	}

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<BlogDto> add(@RequestBody BlogDto blogDto, @PathVariable UUID userId,
			@PathVariable UUID categoryId) {
		BlogDto newBlogDto = blogService.add(blogDto, userId, categoryId);
		return new ResponseEntity<BlogDto>(newBlogDto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/user/{userId}/category/{categoryId}")
	public ResponseEntity<BlogDto> update(@RequestBody BlogDto blogDto, @PathVariable UUID id,
			@PathVariable UUID userId, @PathVariable UUID categoryId) {
		BlogDto updateBlogDto = blogService.update(blogDto, categoryId, id);
		return new ResponseEntity<BlogDto>(updateBlogDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
		blogService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Blog deleted Successfully", true), HttpStatus.OK);
	}

	@PostMapping("/image/upload/{blogId}")
	public ResponseEntity<BlogDto> uploadBlogImage(@RequestParam("image") MultipartFile image,
			@PathVariable UUID blogId) throws IOException {
		BlogDto blogDto = blogService.findById(blogId);
		String fileName = commonImageService.uploadImage(WebConstants.IMAGE_THUM_STRING, image);
		if (!blogDto.getThumbnail().equals("default")) {
			commonImageService.deleteImage(WebConstants.IMAGE_THUM_STRING, blogDto.getThumbnail());
		}
		blogDto.setThumbnail(fileName);
		BlogDto updateBlogDto = blogService.updateThumbnail(blogDto, blogId);
		return new ResponseEntity<BlogDto>(updateBlogDto, HttpStatus.OK);
	}

	// serve image using restapi
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.commonImageService.getResources(WebConstants.IMAGE_THUM_STRING, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

//	@PostMapping("/images/upload/{blogId}")
//	public ResponseEntity<BlogDto> uploadBlogImages(
//			@RequestParam("image") MultipartFile[] images,
//			@PathVariable UUID blogId
//			) throws IOException
//	{
//		BlogDto blogDto = blogService.findById(blogId);
//		for (MultipartFile image : images) {
//			if(!blogDto.getImages().contains(image))
//				commonImageService.uploadInBlogImage(blogId, "src/main/resources/static/images/thumbnailPost", image);
//		}
//		BlogDto updatedBlogDto = blogService.findById(blogId);
//		return new ResponseEntity<BlogDto>(updatedBlogDto,HttpStatus.OK);
//	}
}
