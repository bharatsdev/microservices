package com.ms.common.auth.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String jwttoken;

}
