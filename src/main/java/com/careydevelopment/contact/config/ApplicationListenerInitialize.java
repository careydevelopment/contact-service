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
    	SalesOwner owner = userService.fetchUser("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJ0aCIsImF1ZCI6ImNhcmV5ZGV2ZWxvcG1lbnQtZWNvc3lzdGVtLXVzZXJzIiwiaWQiOiI1Zjc4ZDhmYmMxZDMyNDZhYjQzMDNmMmIiLCJleHAiOjE2MTEzNDc5NDMsImlhdCI6MTYxMTI2MTU0MywiYXV0aG9yaXRpZXMiOlsiQ0FSRVlERVZFTE9QTUVOVF9DUk1fVVNFUiJdfQ.1x9rkEB3abEjMiRRtvSwgg4FFLPLdVT-jNDIztIRFF2dgqn50IVR50wmNXcinLBqSzD0KS_q1Uf6kbXA6UBgOQ");
    	
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