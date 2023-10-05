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
public class BlogDto {
	private UUID id;
	private UserDto owner;
	private CategoryDto category;
	private String title;
	private String text;
	private String thumbnail;
	private Boolean isDeleted = false;
	private Timestamp modifiedTime;
	private Timestamp createdTime;
	private Set<CommentDto> comments = new HashSet<>();
	private Set<ReactionDto> reations = new HashSet<>();
	private Set<ImageDto> images = new HashSet<>();
}
