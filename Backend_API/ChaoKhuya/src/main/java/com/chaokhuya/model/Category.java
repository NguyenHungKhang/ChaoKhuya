package com.chaokhuya.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "`desc`", nullable = false)
	private String desc;
	
	@Column(name = "isdeleted" , nullable = false)
	private Boolean isDeleted = false;
	
	@CreationTimestamp
	@Column(name = "createdtime")
	private Timestamp createdTime;
	
	@UpdateTimestamp
	@Column(name = "modifiedtime")
	private Timestamp modifiedTime;
	
	@OneToMany(mappedBy = "category")
    private List<Blog> blogs;
}
