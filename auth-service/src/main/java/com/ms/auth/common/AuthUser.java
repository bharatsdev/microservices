package com.ms.auth.common;

import lombok.Data;

@Data
public class AuthUser {
	private Integer id;
	private String username, password;
	private String role;
}
