package com.chaokhuya.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "Role")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@CreationTimestamp
	@Column(name = "createdtime")
	private Timestamp createdTime;
	
	@UpdateTimestamp
	@Column(name = "modifiedtime")
	private Timestamp modifiedTime;
}
