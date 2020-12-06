package com.careydevelopment.contact.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@environment.getProperty('mongo.address.collection')}")
public class Address {

    @NotEmpty(message = "Please enter a valid street address")
    @Size(max = 80, message = "Street address cannot exceed 80 characters")
	private String street1;
    
    @Size(max = 80, message = "Street address cannot exceed 80 characters")
	private String street2;
	
	@NotEmpty(message = "Please enter a valid city")
    @Size(max = 50, message = "City name cannot exceed 50 characters")
	private String city;
	
	@NotEmpty(message = "Please select a state")
    @Size(max = 2, message = "State must be a two-letter abbreviation")
	private String state;
	
	private String zip;
	
    @Size(max = 2, message = "Country must be a 2-letter abbreviation")
	private String country;
	
	private AddressType addressType;
	
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
