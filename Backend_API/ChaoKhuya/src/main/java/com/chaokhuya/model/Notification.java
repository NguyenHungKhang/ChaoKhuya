package com.chaokhuya.model;

import java.sql.Timestamp;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "Notification")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="ownerid")
	private User owner;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "url", nullable = true)
	private String url;
	
	@Column(name = "ischecked" , nullable = false)
	private Boolean isChecked = false;
	
	@CreationTimestamp
	@Column(name = "createdtime")
	private Timestamp createdTime;
	
	@UpdateTimestamp
	@Column(name = "modifiedtime")
	private Timestamp modifiedTime;
}
