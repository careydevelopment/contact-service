package com.careydevelopment.contact.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@environment.getProperty('mongo.phone.collection')}")
public class Phone {

    @NotEmpty(message = "Please enter a phone number")
    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
	private String phone;
	private PhoneType phoneType;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public PhoneType getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
