package com.chaokhuya.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface ICommonImageService {
	String uploadImage(String path, MultipartFile file) throws IOException;
	void deleteImage(String path, String fileName) throws IOException;
	InputStream getResources(String path, String fileName) throws FileNotFoundException;
//	String uploadInBlogImage(UUID blogId,String path, MultipartFile file) throws IOException;
//	void deleteInBlogImage(UUID imageId, String path) throws IOException;
//	InputStream getResourcesInBlogImage(UUID imageId, String path) throws IOException;
}
