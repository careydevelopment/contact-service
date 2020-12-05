package com.careydevelopment.ecosystem.user.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import com.careydevelopment.ecosystem.user.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
	
	private static final String AUDIENCE = "careydevelopment-ecosystem-users";
	private static final String ISSUER = "careydevelopment";
	
	public static String SECRET;
	
	private String token = null;
	
	
	public JwtTokenUtil(String jwtToken) {
		this.token = jwtToken;
	}
	
	
	public String getToken() {
	    return token;
	}
	
	
	public String getUsernameFromToken() {
		return getClaimFromToken(Claims::getSubject);
	}

	
	public Date getExpirationDateFromToken() {
		return getClaimFromToken(Claims::getExpiration);
	}

	
	public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken();
		return claimsResolver.apply(claims);
	}
	
	
	public String getClaimFromTokenByName(String name) {
		final Claims claims = getAllClaimsFromToken();
		return (String)claims.get(name);
	}
	
	
	private Claims getAllClaimsFromToken() {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	
	private Boolean isTokenExpired() {
		final Date expiration = getExpirationDateFromToken();
		return expiration.before(new Date());
	}

	
	public static JwtTokenUtil generateToken(User user) {
		Map<String, Object> claims = addClaims(user); 
		return doGenerateToken(claims, user.getUsername());
	}

	
	private static Map<String, Object> addClaims(User user) {
		Map<String, Object> claims = new HashMap<String, Object>();
		
		claims.put("id", user.getId());
		claims.put("authorities", user.getAuthorityNames());
		
		return claims;
	}	

	
	private static JwtTokenUtil doGenerateToken(Map<String, Object> claims, String subject) {
		String token= Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuer(ISSUER)
				.setAudience(AUDIENCE)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		JwtTokenUtil jwtUtil = new JwtTokenUtil(token);
		return jwtUtil;
	}

	
	public Boolean validateToken(UserDetails userDetails) {
		final String username = getUsernameFromToken();
		return (userDetails != null && username.equals(userDetails.getUsername()) && !isTokenExpired());
	}
	
	
	public Boolean validateToken() {
		return (!isTokenExpired());
	}
}
 