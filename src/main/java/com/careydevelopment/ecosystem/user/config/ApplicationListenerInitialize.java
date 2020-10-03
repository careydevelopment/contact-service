package com.careydevelopment.ecosystem.user.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.careydevelopment.ecosystem.user.model.User;
import com.careydevelopment.ecosystem.user.repository.UserRepository;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent>  {
	
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;
	
	
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<String> authorities = new ArrayList<String>();
        authorities.add("JWT_USER");      

    	User user = new User();
    	user.setAuthorityNames(authorities);
    	user.setCity("Detroit");
    	user.setEmail("darth@xmail.com");
    	user.setPhoneNumber("474-555-1212");
    	user.setState("MI");
    	user.setStreet1("123 Main St.");
    	user.setZip("36555");
    	user.setCountry("United States");
    	user.setFirstName("Darth");
    	user.setLastName("Vader");
    	user.setPassword(encoder.encode("thedarkside"));
    	user.setUsername("darth");

    	//userRepository.insert(user);
    }
}