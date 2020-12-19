package com.careydevelopment.contact.exception;

import org.springframework.security.core.AuthenticationException;

public class ContactServiceAuthenticationException extends AuthenticationException {
    
    private static final long serialVersionUID = 6356844005269578058L;

    public ContactServiceAuthenticationException(String s) {
        super(s);
    }
}
