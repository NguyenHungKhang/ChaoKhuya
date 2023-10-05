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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "Blog")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="ownerid")
	private User owner;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	
	@Column(name = "title" , nullable = false)
	private String title;
	
	@Column(name = "text" , nullable = false)
	private String text;
	
	@Column(name = "thumbnail", nullable = false)
	private String thumbnail;
	
	@Column(name = "isdeleted", nullable = false)
	private Boolean isDeleted = false;
	
	@CreationTimestamp
	@Column(name = "createdtime")
	private Timestamp createdTime;
	
	@UpdateTimestamp
	@Column(name = "modifiedtime")
	private Timestamp modifiedTime;
	
	@OneToMany(mappedBy = "blog")
    private List<Comment> comments;
	
	@OneToMany(mappedBy = "blog")
    private List<Reaction> reations;
	
	@OneToMany(mappedBy = "blog")
    private List<Saved> savedBlogs;
	
	@OneToMany(mappedBy = "blog")
    private List<Image> images;
}
