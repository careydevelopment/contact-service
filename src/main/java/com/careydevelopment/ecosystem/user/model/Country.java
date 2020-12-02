package com.careydevelopment.ecosystem.user.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@environment.getProperty('mongo.country.collection')}")
public class Country {

	private String name;
	private String twoLetterCode;
	private String threeLetterCode;
	private String countryCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTwoLetterCode() {
		return twoLetterCode;
	}
	public void setTwoLetterCode(String twoLetterCode) {
		this.twoLetterCode = twoLetterCode;
	}
	public String getThreeLetterCode() {
		return threeLetterCode;
	}
	public void setThreeLetterCode(String threeLetterCode) {
		this.threeLetterCode = threeLetterCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
