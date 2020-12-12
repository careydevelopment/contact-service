package com.careydevelopment.contact.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ErrorsResponse {

    private ErrorResponse error;

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
