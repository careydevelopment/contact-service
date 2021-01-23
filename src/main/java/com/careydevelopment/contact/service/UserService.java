package com.careydevelopment.contact.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.util.PropertiesUtil;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    
    private WebClient userClient; 
    
    
    public UserService(@Value("${ecosystem.properties.file.location}") String ecosystemFile) {
        PropertiesUtil propertiesUtil = new PropertiesUtil(ecosystemFile);
        String endpoint = propertiesUtil.getProperty("ecosystem-user-service.endpoint");
        
        userClient = WebClient
	        		.builder()
	        		.baseUrl(endpoint)
	        		.filter(WebClientFilter.logRequest())
	        		.filter(WebClientFilter.logResponse())
	        		.build();
    }
    
    
    public SalesOwner fetchUser(String bearerToken) {
        SalesOwner salesOwner = userClient.get()
                .uri("/user/me")
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .retrieve()
                .bodyToMono(SalesOwner.class)
                .block();
        
        LOG.debug("User is " + salesOwner);
        
        return salesOwner;
    }
    
    
    public SalesOwner createUser(String bearerToken) {
    	SalesOwner salesDude = new SalesOwner();
    	salesDude.setId("id");
    	
        SalesOwner salesOwner = userClient.post()
                .uri("/user")
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(salesDude))
                .retrieve()
                .bodyToMono(SalesOwner.class)
                .block();
        
        LOG.debug("User is " + salesOwner);
        
        return salesOwner;
    }
}
