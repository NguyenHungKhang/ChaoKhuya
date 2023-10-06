package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, UUID>{
	@Query(nativeQuery = true, value = "SELECT * FROM comment c WHERE c.blogid = ?1 AND c.commentid = null")
	Page<Comment> findParentCommentByBlog(UUID blogId, Pageable pageable);
	Page<Comment> findByOwner(UUID ownerId, Pageable pageable); 
	Page<Comment> findByComment(UUID commentId, Pageable pageable);
}
