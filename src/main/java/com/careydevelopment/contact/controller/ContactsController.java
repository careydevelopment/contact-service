package com.careydevelopment.contact.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contact")
public class ContactsController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ContactsController.class);
        

}
