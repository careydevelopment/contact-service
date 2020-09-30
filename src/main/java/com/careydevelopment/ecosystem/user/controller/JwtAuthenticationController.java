package com.careydevelopment.ecosystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.ecosystem.user.model.JwtRequest;
import com.careydevelopment.ecosystem.user.model.JwtResponse;
import com.careydevelopment.ecosystem.user.model.User;
import com.careydevelopment.ecosystem.user.util.JwtTokenUtil;



@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final User user = authenticate(authenticationRequest);
		final JwtTokenUtil jwtTokenUtil = JwtTokenUtil.generateToken(user);
		final String token = jwtTokenUtil.getToken();
		Long expirationDate = jwtTokenUtil.getExpirationDateFromToken().getTime();
		
		return ResponseEntity.ok(new JwtResponse(token, user, expirationDate));
	}

	
	private User authenticate(JwtRequest request) throws Exception {
		User user = null;
		
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			user = (User)auth.getPrincipal();
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		return user;
	}
}