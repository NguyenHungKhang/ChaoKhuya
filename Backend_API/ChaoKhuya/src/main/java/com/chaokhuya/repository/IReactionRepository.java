package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Reaction;

@Repository
public interface IReactionRepository extends JpaRepository<Reaction, UUID>{
	Page<Reaction> findByOwner(UUID ownerId, Pageable pageable);
	Page<Reaction> findByBlog(UUID blogId, Pageable pageable);
}
