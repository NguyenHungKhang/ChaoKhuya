package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Saved;

@Repository
public interface ISavedRepository extends JpaRepository<Saved, UUID>{
	Page<Saved> findByOwner(UUID ownerId, Pageable pageable);
}
