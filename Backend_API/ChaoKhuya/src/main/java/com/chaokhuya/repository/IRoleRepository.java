package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID>{

}
