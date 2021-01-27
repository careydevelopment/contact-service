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
            SalesOwner owner = userService.fetchUser("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJ0aCIsImF1ZCI6ImNhcmV5ZGV2ZWxvcG1lbnQtZWNvc3lzdGVtLXVzZXJzIiwiaWQiOiI1Zjc4ZDhmYmMxZDMyNDZhYjQzMDNmMmIiLCJleHAiOjE2MTE3OTAyMDAsImlhdCI6MTYxMTcwMzgwMCwiYXV0aG9yaXRpZXMiOlsiQ0FSRVlERVZFTE9QTUVOVF9DUk1fVVNFUiJdfQ.ENmihdJQkscMN0IAnjvnnM2Ys5QweTQ_-CmaOL9DXut9eiVtrAgo2pUDXQFcPMemA7YY2aQt8h7TlkWzVyIeMg");
            ObjectMapper objectMapper = new ObjectMapper();
            System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(owner));
        } catch (ServiceException e) {
            System.err.println(e.getMessage() + " " + e.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}