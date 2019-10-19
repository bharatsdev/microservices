package com.everythingisdata.auth.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.everythingisdata.auth.common.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
 
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

	@Autowired
	AuthenticationManager authenticationManager;

	private final JwtConfig jwtConfig;

	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
			JwtConfig jwtConfig) {
		log.info("[INFO] : JwtUsernameAndPasswordAuthenticationFilter Invoked!"+ jwtConfig.getUri());
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserCredentials credentials;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					credentials.getUsername(), credentials.getPassword(), Collections.emptyList());
			return this.authenticationManager.authenticate(authToken);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		Instant now = Instant.now();
		String authToken = Jwts.builder().setSubject(authResult.getName())
				.claim("authorities",
						authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(jwtConfig.getExpiration())))
				.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()).compact();
		resp.addHeader("access-control-expose-headers", "Authorization");
		resp.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + " " + authToken);
	}

	@Getter
	@Setter
	private static class UserCredentials {
		private String username, password;
	}
}
