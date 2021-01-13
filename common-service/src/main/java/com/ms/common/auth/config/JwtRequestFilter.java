
package com.ms.common.auth.config;

import com.ms.common.auth.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bharat2010
 */
@Service
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final String HEADER = "Authorization";
	private static final String BEARER = "Bearer ";

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req,  HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		final String jwtRequestTokenHeader = req.getHeader(HEADER);
		String username = null;
		String jwtToken = null;

		if (jwtRequestTokenHeader != null && jwtRequestTokenHeader.startsWith(BEARER)) {
			jwtToken = jwtRequestTokenHeader.substring(BEARER.length());
			try {
				username = jwtTokenUtil.retrieveUserNameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
			}
		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		chain.doFilter(req, resp);
	}
}
