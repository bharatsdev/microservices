package com.everythingisdata.auth.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.everythingisdata.auth.common.AuthUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String authUser) throws UsernameNotFoundException {
		log.info("[INFO] : loadUserByUsername invoked.....!");

		// hard coding the users. All passwords must be encoded.
		final List<AuthUser> AuthUsers = Arrays.asList(new AuthUser(1, "bharat", encoder.encode("bharat"), "USER"),
				new AuthUser(2, "singh", encoder.encode("singh"), "ADMIN"));

		Optional<AuthUser> objUser = AuthUsers.stream().filter(user -> user.getUsername().equals(authUser)).findAny();

		if (objUser.isPresent()) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_" + objUser.get().getRole());
			return new User(objUser.get().getUsername(), objUser.get().getPassword(), grantedAuthorities);

		}

		throw new UsernameNotFoundException("Username: " + authUser + " not found");

	}

}
