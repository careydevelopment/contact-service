package com.careydevelopment.ecosystem.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careydevelopment.ecosystem.user.model.User;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		return user;
	}
	
	
	private User findByUsername(String username) {
	    User user = null;
	    
	    if ("johnny".equals(username)) {
	        user = getJohnny();
	    }
	    
	    return user;
	}
	
	
	private User getJohnny() {
	    User user = new User();
	    
	    user.setFirstName("Johnny");
	    user.setLastName("Smith");
	    user.setId("1");
	    user.setUsername("johnny");
	    user.setPassword(encoder.encode("kleptocracy"));
	    
	    List<String> authorityNames = new ArrayList<String>();
	    authorityNames.add("JWT_USER");
	    user.setAuthorityNames(authorityNames);
	    
	    return user;
	}
}
