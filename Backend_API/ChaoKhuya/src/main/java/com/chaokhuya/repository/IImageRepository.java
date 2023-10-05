package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Image;

@Repository
public interface IImageRepository extends JpaRepository<Image, UUID>{

}
