package com.chaokhuya.payload;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDto {
	private UUID id;
	private String filename;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
}
