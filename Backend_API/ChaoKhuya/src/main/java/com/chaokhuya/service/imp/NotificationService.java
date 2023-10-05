package com.chaokhuya.service.imp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chaokhuya.model.Blog;
import com.chaokhuya.model.Notification;
import com.chaokhuya.model.User;
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.NotificationDto;
import com.chaokhuya.payload.PageResponse;
import com.chaokhuya.repository.INotificationRepository;
import com.chaokhuya.repository.IUserRepository;
import com.chaokhuya.service.INotificationService;

@Service
public class NotificationService implements INotificationService {
	
	@Autowired
	private INotificationRepository notificationRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Notification DtoToNotification(NotificationDto notificationDto) {
		Notification notification = modelMapper.map(notificationDto, Notification.class);
		return notification;
	}
	
	private NotificationDto NotificationToDto(Notification notification) {
		NotificationDto notificationDto = modelMapper.map(notification, NotificationDto.class);
		return notificationDto;
	}
	
	@Override
	public List<NotificationDto> getAll() {
		List<Notification> notificationList = notificationRepository.findAll();
		List<NotificationDto> notificationDtoList = notificationList.stream().map(notification -> this.NotificationToDto(notification)).collect(Collectors.toList());
		return notificationDtoList;
	}

	@Override
	public NotificationDto add(NotificationDto notificationDto, UUID ownerId) {
		User user = userRepository.findById(ownerId).get();
		Notification notification = this.DtoToNotification(notificationDto);
		notification.setOwner(user);
		Notification newNotification = notificationRepository.save(notification);
		return this.NotificationToDto(newNotification);
	}

	@Override
	public NotificationDto update(NotificationDto notificationDto, UUID id) {
		Notification notification = notificationRepository.findById(id).get();
		notification.setText(notificationDto.getText());
		notification.setType(notification.getType());
		notification.setUrl(notificationDto.getUrl());
		Notification updateNotification = notificationRepository.save(notification);
		return this.NotificationToDto(updateNotification);
	}

	@Override
	public void delete(UUID id) {
		Notification notification = notificationRepository.findById(id).get();
		notificationRepository.delete(notification);
	}

	@Override
	public NotificationDto findById(UUID id) {
		Notification notification = notificationRepository.findById(id).get();
		return this.NotificationToDto(notification);
	}

	@Override
	public PageResponse<NotificationDto> getByOwner(UUID ownerId, int page, int size) {
		Sort sort = Sort.by("createdtTime").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Notification> notificationList = notificationRepository.findByOwner(ownerId, pageable);
		List<NotificationDto> notificationDtoList = notificationList.stream().map((notification) -> this.NotificationToDto(notification)).collect(Collectors.toList());
		return new PageResponse<>(notificationDtoList, notificationList.getNumber(), notificationList.getSize(), notificationList.getTotalElements(),
				notificationList.getTotalPages(), notificationList.isLast());
	}

	@Override
	public NotificationDto check(UUID id) {
		Notification notification = notificationRepository.findById(id).get();
		notification.setIsChecked(true);
		Notification updateNotification = notificationRepository.save(notification);
		return this.NotificationToDto(updateNotification);
	}

}
