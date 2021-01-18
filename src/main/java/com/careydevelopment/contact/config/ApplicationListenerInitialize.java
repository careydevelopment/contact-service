package com.careydevelopment.contact.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.model.Source;
import com.careydevelopment.contact.repository.ContactRepository;
import com.careydevelopment.contact.service.ContactService;
import com.careydevelopment.contact.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent>  {
	
	@Autowired
	private ContactService contactService;
	
	
    public void onApplicationEvent(ApplicationReadyEvent event) {    	
    	List<SalesOwner> owners = contactService.findSalesOwnersBySource(Source.EMAIL);
    	
    	try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	    	System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(owners));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}