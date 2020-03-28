package com.ms.common.auth.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class JwtRequest {

	private String username;
	private String password;

}
