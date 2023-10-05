package com.chaokhuya.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chaokhuya.model.Blog;
import com.chaokhuya.model.Image;
import com.chaokhuya.payload.ImageDto;
import com.chaokhuya.repository.IBlogRepository;
import com.chaokhuya.repository.IImageRepository;
import com.chaokhuya.service.ICommonImageService;

@Service
public class CommonImageService implements ICommonImageService {
	
	@Autowired
	private IImageRepository imageRepository;
	
	@Autowired
	private IBlogRepository blogRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	private Image DtoToImage(ImageDto imageDto) {
//		Image image = modelMapper.map(imageDto, Image.class);
//		return image;
//	}
//	
//	private ImageDto ImageToDto(Image image) {
//		ImageDto imageDto = modelMapper.map(image, ImageDto.class);
//		return imageDto;
//	}
	
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		String extention = name.substring(name.lastIndexOf('.'));
		if (!(extention.equals(".png") || extention.equals(".jpg") || extention.equals(".jpeg"))) {
			System.out.print("not a image");
		}

		String randomId = UUID.randomUUID().toString();
		String modifiedFileName = randomId.concat(name.substring(name.lastIndexOf('.')));

		
		String filePath = path + File.separator + modifiedFileName;
		File f = new File(path);
		
		if (!f.exists()) {
			f.mkdir();
		}
		System.out.print(f.getAbsolutePath());

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return modifiedFileName;
	}
	
	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}

	@Override
	public void deleteImage(String path, String fileName) throws IOException {
		Path filePath = Paths.get(path + File.separator + fileName);
		Files.delete(filePath);
	}

//	@Override
//	public String uploadInBlogImage(UUID blogId, String path, MultipartFile file) throws IOException {
//		Blog blog = blogRepository.findById(blogId).get();
//		Image image = new Image();
//		image.setBlog(blog);
//		String filename = this.uploadImage(path, file);
//		image.setFilename(filename);
//		imageRepository.save(image);
//		return filename;
//		
//	}
//
//	@Override
//	public void deleteInBlogImage(UUID imageId, String path) throws IOException {
//		Image image = imageRepository.findById(imageId).get();
//		this.deleteImage(path, image.getFilename());
//		imageRepository.delete(image);
//	}
//
//	@Override
//	public InputStream getResourcesInBlogImage(UUID imageId, String path) throws IOException {
//		Image image = imageRepository.findById(imageId).get();
//		return this.getResources(path, image.getFilename());
//	}

}
