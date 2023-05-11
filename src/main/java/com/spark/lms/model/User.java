package com.spark.lms.model;

import com.spark.lms.model.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {}
	
	public User(@NotNull String displayName, @NotNull String username, @NotNull String password, @NotNull String role) {
		super();
		this.displayName = displayName;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "display_name")
	private String displayName;
	
	@NotNull
	@Column(name = "username")
	private String username;
	
	@NotNull
	@Column(name = "password")
	private String password;
	
	@NotNull
	@Column(name = "active")
	private Integer active;
	
	@NotNull
	@Column(name = "role")
	private String role;
	
	@NotNull
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "last_modified_date")
	private Date lastModifiedDate;
	public static User toUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setDisplayName(userDto.getDisplayName());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setActive(userDto.getActive());
		user.setRole(userDto.getRole());
		user.setCreatedDate(userDto.getCreatedDate());
		user.setLastModifiedDate(userDto.getLastModifiedDate());
		return user;
	}
}
