package com.careydevelopment.contact.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class JwtResponse {

	private String token;
	private Object user;
	private Long expirationDate;

	
	public void setToken(String token) {
        this.token = token;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public String getToken() {
		return this.token;
	}

	public Object getUser() {
		return user;
	}	

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
