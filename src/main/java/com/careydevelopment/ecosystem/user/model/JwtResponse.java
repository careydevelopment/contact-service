package com.careydevelopment.ecosystem.user.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class JwtResponse {

	private final String token;
	private final User user;
	private final Long expirationDate;

	public JwtResponse(String token, User user, Long expirationDate) {
		this.token = token;
		this.user = user;
		this.expirationDate = expirationDate;
	}
	
	public Long getExpirationDate() {
        return expirationDate;
    }

    public String getToken() {
		return this.token;
	}

	public User getUser() {
		return user;
	}	

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
