package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID>{

}
