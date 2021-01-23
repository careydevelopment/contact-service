package com.careydevelopment.contact.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.util.PropertiesUtil;


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
    	try {
	        SalesOwner salesOwner = userClient.get()
	                .uri("/user/me")
	                .header(HttpHeaders.AUTHORIZATION, bearerToken)
	                .retrieve()
	                .bodyToMono(SalesOwner.class)
	                .block();
	        
	        LOG.debug("User is " + salesOwner);
	        
	        return salesOwner;
    	} catch (WebClientResponseException we) {
    		throw new ServiceException(we.getMessage(), we.getRawStatusCode());
    	} 
    }
}
