package com.careydevelopment.contact.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.util.PropertiesUtil;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    
    private WebClient userClient; 
    
    
    public UserService(@Value("${ecosystem.properties.file.location}") String ecosystemFile) {
        PropertiesUtil propertiesUtil = new PropertiesUtil(ecosystemFile);
        String endpoint = propertiesUtil.getProperty("ecosystem-user-service.endpoint");
        userClient = WebClient.create(endpoint);
    }
    
    
    public SalesOwner fetchUser(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        
        SalesOwner salesOwner = userClient.get()
                .uri("/user/me")
                .header(HttpHeaders.AUTHORIZATION, requestTokenHeader)
                .retrieve()
                .bodyToMono(SalesOwner.class)
                .block();
        

        LOG.debug("User is " + salesOwner);
        
        return salesOwner;
    }
}
