package com.ms.common.auth.config;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

/**
 * @author Bharat2010
 *
 */
//
//@Service
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = -5544219867859084380L;
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * org.springframework.security.web.AuthenticationEntryPoint#commence(javax.
//	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
//	 * org.springframework.security.core.AuthenticationException)
//	 */
//	@Override
//	public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException auth)
//			throws IOException {
//
//		resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//	}
//
//}
