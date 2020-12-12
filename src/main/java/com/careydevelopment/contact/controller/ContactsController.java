package com.careydevelopment.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.contact.model.Contact;
import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.repository.ContactRepository;
import com.careydevelopment.contact.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class ContactsController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ContactsController.class);
        

    @Autowired
    private UserService userService;
    
    @Autowired
    private ContactRepository contactRepository;
    
    
    @PostMapping("/")
    public ResponseEntity<?> createContact(@Valid @RequestBody Contact contact, HttpServletRequest request) {
        LOG.debug("Creating new contact: " + contact);
        
        SalesOwner salesOwner = userService.fetchUser(request);
        contact.setSalesOwner(salesOwner);
        
        Contact savedContact = contactRepository.insert(contact);
        
        return ResponseEntity.ok().body(savedContact);
    }
    
}
