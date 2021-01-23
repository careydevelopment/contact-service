package com.careydevelopment.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.service.ServiceException;
import com.careydevelopment.contact.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent>  {
	
	@Autowired
	private UserService userService;
	
    public void onApplicationEvent(ApplicationReadyEvent event) {        	
    	
    	try {
	    	SalesOwner owner = userService.fetchUser("Bearer eyJhbGciOiJIUzUx...");
	    	
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	    	System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(owner));
    	} catch (ServiceException se) {
    		System.err.println("Error: " + se.getStatusCode() + " " + se.getMessage());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}