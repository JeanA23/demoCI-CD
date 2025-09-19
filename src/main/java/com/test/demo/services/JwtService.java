package com.test.demo.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${spring.jwt.security.key}")
	private String secretKey;
	
	public String generateToken(UserDetails userDetails) {
		
		return generateToken(new HashMap<>(), userDetails);
	}

	private String generateToken(Map<String ,Object> extraClaims, UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();
		
		List<String> roles = new ArrayList<>();
		
		for(GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.add(authority.getAuthority());
		}
		
		claims.put("roles", roles);
		claims.putAll(extraClaims);
		
		
		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSingInKey())
				.compact();
	}

	private Key getSingInKey() {

		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
 