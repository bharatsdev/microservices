package com.everythingisdata.gateway.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.everythingisdata.gateway.common.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);

	private final JwtConfig jwtConfig;

	public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) { 
		log.info("[INFO] : JwtTokenAuthenticationFilter Invoked!" + jwtConfig.toString());
 		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		log.info("[INFO] : DoFilterInternal Invoked >>>!" + jwtConfig.getUri());

		// 1. get the authentication header. Tokens are supposed to be passed in the
		// authentication header
		String headerString = req.getHeader(jwtConfig.getHeader());
		System.out.println(headerString);
		log.info(headerString);

		if (headerString == null || !headerString.startsWith(jwtConfig.getPrefix())) {
			chain.doFilter(req, resp); // If not valid, go to the next filter.
			return;
		}

		// Get the token
		String authToken = headerString.replace(jwtConfig.getPrefix(), "");
		// exceptions might be thrown in creating the claims if the token is expired
		try {
			// token validation
			Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(authToken)
					.getBody();
			String userName = claims.getSubject();
			if (null != userName) {

				@SuppressWarnings("unchecked")
				List<String> authorities = claims.get("authorities", List.class);

				// UsernamePasswordAuthenticationToken: A built-in object, used by spring to
				// represent the current authenticated / being authenticated user.
				// It needs a list of authorities, which has type of GrantedAuthority interface,
				// where SimpleGrantedAuthority is an implementation of that interface

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

				SecurityContextHolder.getContext().setAuthentication(auth);

			}

		} catch (Exception e) {
			// Make Sure it's clear. So,in case of failure,user won't be authenticated
			SecurityContextHolder.clearContext();
		}
		// Move to the next filter in the filter chain

		chain.doFilter(req, resp);
	}

}
