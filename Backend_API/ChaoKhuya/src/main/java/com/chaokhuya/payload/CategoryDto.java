package com.chaokhuya.payload;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
	private UUID id;
	private String name;
	private String desc;
	private Boolean isDeleted = false;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
}
