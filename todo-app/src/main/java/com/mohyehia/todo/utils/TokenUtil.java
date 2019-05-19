package com.mohyehia.todo.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {
	
	private final String CLAIMS_SUBJECT = "sub";
	private final String CLAIMS_CREATED = "created";
	
	@Value("${auth.expiration}")
	private Long TOKEN_VALIDITY = 604800L;
	
	@Value("${auth.secret}")
	private String TOKEN_SECRET;
	
	public String generateToken(UserDetails userDetails) {
		/**
		 * 1 => claims
		 * 2 => expiration
		 * 3 => sign
		 * 4 => compact
		 */
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
		claims.put(CLAIMS_CREATED, new Date());
		
		return Jwts.builder()
			.setClaims(claims)
			.setExpiration(getExpirationDate())
			.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
			.compact();
	}
	
	public String getUsernameFromToken(String token) {
		try {
			return getClaims(token).getSubject();
		} catch (Exception e) {
			return null;
		}
	}

	private Date getExpirationDate() {
		return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expirationDate = getClaims(token).getExpiration();
		return expirationDate.before(new Date());
	}
	
	private Claims getClaims(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
}
