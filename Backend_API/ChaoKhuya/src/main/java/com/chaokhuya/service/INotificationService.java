package com.chaokhuya.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chaokhuya.model.Notification;
import com.chaokhuya.payload.NotificationDto;
import com.chaokhuya.payload.PageResponse;

public interface INotificationService {
	List<NotificationDto> getAll();
	PageResponse<NotificationDto> getByOwner(UUID ownerId, int page, int size);
	NotificationDto add(NotificationDto notificationDto, UUID ownerId);
	NotificationDto update(NotificationDto notificationDto, UUID id);
	NotificationDto check(UUID id);
	void delete(UUID id);
	NotificationDto findById(UUID id);
}
