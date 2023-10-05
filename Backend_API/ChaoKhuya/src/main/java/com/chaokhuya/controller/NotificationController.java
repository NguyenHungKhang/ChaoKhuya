package com.chaokhuya.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaokhuya.payload.ApiResponse;
import com.chaokhuya.payload.BlogDto;
import com.chaokhuya.payload.NotificationDto;
import com.chaokhuya.service.INotificationService;
import com.chaokhuya.service.imp.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	INotificationService notificationService = new NotificationService();
	
	@GetMapping("/")
	public ResponseEntity<List<NotificationDto>> getAll(){
		List<NotificationDto> notificationDtoList = notificationService.getAll();
		return new ResponseEntity<List<NotificationDto>>(notificationDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NotificationDto> findById(@PathVariable UUID id){
		NotificationDto notificationDto = notificationService.findById(id);
		return new ResponseEntity<NotificationDto>(notificationDto, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/")
	public ResponseEntity<NotificationDto> add(@RequestBody NotificationDto notificationDto, @PathVariable UUID userId){
		NotificationDto newNotificationDto = notificationService.add(notificationDto, userId);
		return new ResponseEntity<NotificationDto>(newNotificationDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<NotificationDto> update(@RequestBody NotificationDto notificationDto, @PathVariable UUID id){
		NotificationDto updateNotificationDto= notificationService.update(notificationDto, id);
		return new ResponseEntity<NotificationDto>(updateNotificationDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable UUID id){
		notificationService.delete(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Notification deleted Successfully",true),HttpStatus.OK);
	}
}
