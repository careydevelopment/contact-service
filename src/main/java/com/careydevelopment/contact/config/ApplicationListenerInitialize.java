package com.careydevelopment.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.service.ServiceException;
import com.careydevelopment.contact.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationListenerInitialize implements ApplicationListener<ApplicationReadyEvent>  {
    
    @Autowired
    private UserService userService;
    
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            SalesOwner owner = userService.fetchUser("Bearer eyJhbGciOiJIUz...");
            ObjectMapper objectMapper = new ObjectMapper();
            System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(owner));
        } catch (ServiceException e) {
            System.err.println(e.getMessage() + " " + e.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
