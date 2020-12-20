package com.careydevelopment.contact.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.careydevelopment.contact.model.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

    public Contact findByEmail(String email);
    
    public List<Contact> findBySalesOwnerUsername(String username);

}
