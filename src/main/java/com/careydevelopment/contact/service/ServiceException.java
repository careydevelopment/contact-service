package com.careydevelopment.contact.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -7661881974219233311L;

	private int statusCode;
	
	public ServiceException (String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
