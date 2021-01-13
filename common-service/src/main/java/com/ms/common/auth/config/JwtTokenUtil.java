
package com.ms.common.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Bharat2010
 *
 */
@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -8451006447055499413L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60L;

	@Value("${jwt.secret}")
	private String jwtKey;

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().addClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS256, jwtKey).compact();

	}

	public String generateJwtTokens(UserDetails details) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, details.getUsername());
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}

	public String retrieveUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = retrieveUserNameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
