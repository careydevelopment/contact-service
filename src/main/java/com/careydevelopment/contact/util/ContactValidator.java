package com.careydevelopment.contact.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.careydevelopment.contact.model.Contact;
import com.careydevelopment.contact.model.ErrorResponse;
import com.careydevelopment.contact.model.ValidationError;
import com.careydevelopment.contact.repository.ContactRepository;

@Component
public class ContactValidator {

    @Autowired
    private ContactRepository contactRepository;
    
    public ErrorResponse validateContact(Contact contact) {
        ErrorResponse errorResponse = new ErrorResponse();
        contact = (Contact)SpaceUtil.trimReflective(contact);
        
        validateEmail(contact, errorResponse);
        
        if (errorResponse.getErrors().size() == 0) errorResponse = null;
        return errorResponse;
    }
    
    
    private void validateEmail(Contact contact, ErrorResponse errorResponse) {
        if (StringUtils.isEmpty(contact.getId()) && emailExists(contact.getEmail())) {
            addError(errorResponse, "The email you entered already exists", "email", "emailExists");
        }
    }
    
    
    public boolean emailExists(String email) {
        boolean exists = false;
        
        if (email != null && !StringUtils.isBlank(email)) {
            Contact contact = contactRepository.findByEmail(email);
            exists = (contact != null);
        }
        
        return exists;
    }
    
    
    private void addError(ErrorResponse errorResponse, String errorMessage, String field, String code) {        
        ValidationError validationError = new ValidationError();
        validationError.setCode(code);
        validationError.setDefaultMessage(errorMessage);
        validationError.setField(field);
        
        errorResponse.getErrors().add(validationError);
    }


    public ContactRepository getContactRepository() {
        return contactRepository;
    }


    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
