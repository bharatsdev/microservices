package com.everythingisdata.auth.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthUser {
	private Integer id;
	private String username, password;
	private String role;

	public AuthUser(Integer id, String username, String password, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
