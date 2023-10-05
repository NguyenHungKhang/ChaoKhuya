package com.chaokhuya.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "bio", nullable = true)
	private String bio;

	@Column(name = "isenable", nullable = false)
	private Boolean isEnable = false;

	@Column(name = "isdeleted", nullable = false)
	private Boolean isDeleted = false;

	@CreationTimestamp
	@Column(name = "createdtime")
	private Timestamp createdTime;

	@UpdateTimestamp
	@Column(name = "modifiedtime")
	private Timestamp modifiedTime;

	@OneToMany(mappedBy = "owner")
	private List<Blog> blogs;

	@OneToMany(mappedBy = "owner")
	private List<Notification> notificaton;

	@OneToMany(mappedBy = "owner")
	private List<Comment> comments;

	@OneToMany(mappedBy = "owner")
	private List<Reaction> reactions;

	@OneToMany(mappedBy = "owner")
	private List<Saved> savedBlogs;

	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "userrole",
		joinColumns=@JoinColumn(name="userid"),
		inverseJoinColumns = @JoinColumn(name="roleid")
	)
	private Set<Role> roles = new HashSet<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
