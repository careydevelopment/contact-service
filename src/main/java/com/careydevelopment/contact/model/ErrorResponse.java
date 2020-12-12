package com.careydevelopment.contact.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ErrorResponse {

    private String error = "Bad Request";
    private List<ValidationError> errors = new ArrayList<ValidationError>();
    
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public List<ValidationError> getErrors() {
        return errors;
    }
    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    
}
