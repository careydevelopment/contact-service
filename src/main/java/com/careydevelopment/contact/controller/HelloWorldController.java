package com.careydevelopment.contact.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.contact.model.Hello;

@RestController
@CrossOrigin
public class HelloWorldController {
    
    @GetMapping("/helloworld")
    public Hello helloWorld() {
        return new Hello();
    }
}
