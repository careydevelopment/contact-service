package com.careydevelopment.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent>  {
	
	@Autowired
	private UserService userService;
	
    public void onApplicationEvent(ApplicationReadyEvent event) {        	
    	
    	SalesOwner owner = userService.fetchUser("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJ0aCIsImF1ZCI6ImNhcmV5ZGV2ZWxvcG1lbnQtZWNvc3lzdGVtLXVzZXJzIiwiaWQiOiI1Zjc4ZDhmYmMxZDMyNDZhYjQzMDNmMmIiLCJleHAiOjE2MTE0MzEwODksImlhdCI6MTYxMTM0NDY4OSwiYXV0aG9yaXRpZXMiOlsiQ0FSRVlERVZFTE9QTUVOVF9DUk1fVVNFUiJdfQ.h29wdxb2V8wAPFLg48iSgoXwy4hQYQq03s8qYX3jBWWpKzb7KBFbp-R5fpOvD0aU7HdZH3l8UUm5BMOzxwcT5g");
    	
    	try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	    	System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(owner));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}