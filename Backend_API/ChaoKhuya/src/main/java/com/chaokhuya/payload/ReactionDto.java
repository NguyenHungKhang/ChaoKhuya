package com.chaokhuya.payload;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReactionDto {
	private UUID id;
	private UserDto owner;
	private Timestamp createdTime;
	private Timestamp modifedTime;
}
