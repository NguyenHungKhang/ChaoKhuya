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
@Table(name = "Comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="ownerid")
	private User owner;
	
	@ManyToOne
	@JoinColumn(name="blogid")
	private Blog blog;
	
	@ManyToOne
	@JoinColumn(name="commentid")
	private Comment comment;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "isdeleted" , nullable = false)
	private Boolean isDeleted = false;
	
	@CreationTimestamp
	@Column(name = "createdtime")
	private Timestamp createdTime;
	
	@UpdateTimestamp
	@Column(name = "modifiedtime")
	private Timestamp modifiedTime;
	
	@OneToMany(mappedBy = "comment")
    private List<Comment> comments;
}
