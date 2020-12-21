package com.careydevelopment.contact.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.contact.model.Contact;
import com.careydevelopment.contact.model.ErrorResponse;
import com.careydevelopment.contact.model.SalesOwner;
import com.careydevelopment.contact.repository.ContactRepository;
import com.careydevelopment.contact.service.UserService;
import com.careydevelopment.contact.util.ContactValidator;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class ContactsController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ContactsController.class);
        

    @Autowired
    private UserService userService;
    
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private ContactValidator contactValidator;
    
    
    @PostMapping("")
    public ResponseEntity<?> createContact(@Valid @RequestBody Contact contact, HttpServletRequest request) {
        LOG.debug("Creating new contact: " + contact);
        
        ErrorResponse errorResponse = contactValidator.validateContact(contact);
        if (errorResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        
        SalesOwner salesOwner = userService.fetchUser(request);
        contact.setSalesOwner(salesOwner);
        
        Contact savedContact = contactRepository.insert(contact);
        
        return ResponseEntity.ok().body(savedContact);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchContact(@PathVariable("id") String id) {
        LOG.debug("Fetching contact by id: " + id);
        
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if (contactOpt.isPresent()) {
            return ResponseEntity.ok(contactOpt.get());
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable("id") String id, @Valid @RequestBody Contact contact) {
        LOG.debug("Updating contact id: " + id + " with data " + contact);
        
        if (id == null || id.trim().length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID is required");
        } else if (!id.equals(contact.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID in URL and body don't match");
        }

        ErrorResponse errorResponse = contactValidator.validateContact(contact);
        if (errorResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } 
        
        Contact newContact = contactRepository.save(contact);
        
        return ResponseEntity.ok(newContact);
    }

    
    @GetMapping("")
    public ResponseEntity<?> fetchContacts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String)authentication.getPrincipal();
        
        LOG.debug("Fetching all contacts for " + username);
        
        if (!StringUtils.isBlank(username)) {
            List<Contact> contacts = contactRepository.findBySalesOwnerUsername(username);
            return ResponseEntity.ok(contacts);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    
    @PostMapping("/emailcheck")
    public ResponseEntity<?> emailCheck(@RequestBody Map<String, Object> inputData) {
        String email = (String)inputData.get("email");
        LOG.debug("Checking for existence of email " + email);
        
        Boolean bool = contactValidator.emailExists(email);
        
        return ResponseEntity.status(HttpStatus.OK).body(bool); 
    }
}
