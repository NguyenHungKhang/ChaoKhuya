package com.chaokhuya.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaokhuya.model.Notification;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, UUID>{
	Page<Notification> findByOwner(UUID ownerId, Pageable pageable);
}
