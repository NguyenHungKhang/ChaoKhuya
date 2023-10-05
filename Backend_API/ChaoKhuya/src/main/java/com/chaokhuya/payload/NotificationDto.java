package com.chaokhuya.payload;

import java.security.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDto {
	private UUID id;
	private UserDto owner;
	private String type;
	private String text;
	private String url;
	private Boolean isChecked = false;
	private Timestamp createdTime;
	private Timestamp modifedTime;
}
