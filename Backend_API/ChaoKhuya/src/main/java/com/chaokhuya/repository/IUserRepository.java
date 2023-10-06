package com.chaokhuya.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
//	Page<User> findByUsername(String userName, Pageable pageable);
//	Page<User> findAll(Pageable pageable);
}
