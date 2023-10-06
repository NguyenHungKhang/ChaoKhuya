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
public class UserDto {
	private UUID id;
	private String email;
	private String password;
	private String lastname;
	private String firstname;
	private String bio;
	private Boolean isEnable = false;
	private Boolean isDeleted = false;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Set<RoleDto> roles = new HashSet<>();
}
