package com.careydevelopment.contact.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.careydevelopment.contact.model.Contact;
import com.careydevelopment.contact.model.Source;
import com.careydevelopment.contact.service.ContactService;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent>  {
	
	@Autowired
	private ContactService contactService;
	
    public void onApplicationEvent(ApplicationReadyEvent event) {
    	List<Contact> contacts = contactService.findContactsBySource(Source.EMAIL);
    	System.err.println(contacts);
    }

}