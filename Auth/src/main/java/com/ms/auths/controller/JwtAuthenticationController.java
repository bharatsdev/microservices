/**
 * 
 */
package com.ms.auths.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.auth.config.JwtTokenUtil;
import com.ms.auth.model.JwtRequest;
import com.ms.auth.model.JwtResponse;
import com.ms.auth.service.JwtUserDetailsService;

/**
 * @author Bharat2010
 *
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	private ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("---------------");

		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		final String jwtToken = jwtTokenUtil.generateJwtTokens(userDetails);
		return ResponseEntity.ok(new JwtResponse(jwtToken));

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}

}
