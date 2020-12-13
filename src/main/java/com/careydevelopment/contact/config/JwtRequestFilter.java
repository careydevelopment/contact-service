package com.careydevelopment.contact.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.careydevelopment.contact.util.PropertiesUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JwtRequestFilter.class);

	private final String jwtSecret;
	
	
	public JwtRequestFilter(@Value("${ecosystem.properties.file.location}") String propertiesFile) {
	    PropertiesUtil propertiesUtil = new PropertiesUtil(propertiesFile);
        jwtSecret = propertiesUtil.getProperty("jwt.secret");
	}

		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			
			try {
			    //this validates the token	            
			    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
			    
			    Collection<? extends GrantedAuthority> authorities = getAuthorities(claims); 

			    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
                LOG.debug("Authentication token: " + auth);
			    
                SecurityContextHolder.getContext().setAuthentication(auth);

	            chain.doFilter(request, response);
			} catch (IllegalArgumentException e) {

                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
			} catch (ExpiredJwtException e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token expired");
			} catch (SignatureException e) {
	            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid signature");
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	

	private Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
        List<String> authorityNames = (List<String>) claims.get("authorities");
	    
	    Collection<? extends GrantedAuthority> authorities = authorityNames
            .stream()
            .map(auth -> new SimpleGrantedAuthority(auth))
            .collect(Collectors.toList());

	    return authorities;
	}
}
