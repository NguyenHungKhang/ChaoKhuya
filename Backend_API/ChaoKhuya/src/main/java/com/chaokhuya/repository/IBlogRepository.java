package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Blog;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, UUID>{
	Page<Blog> findByOwner(UUID ownerId, Pageable pageable);
	Page<Blog> findByCategory(UUID categoryId, Pageable pageable);
	Page<Blog> findByTitleContaining(String title, Pageable pageable); 
}
