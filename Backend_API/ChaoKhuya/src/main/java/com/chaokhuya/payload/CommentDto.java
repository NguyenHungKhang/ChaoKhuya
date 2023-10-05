package com.chaokhuya.payload;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
	private UUID id;
	private UserDto owner;
	private Set<CommentDto> comments = new HashSet<>();
	private String text;
	private Boolean isDeleted = false;
	private Timestamp createdTime;
	private Timestamp modifedTime;
}
