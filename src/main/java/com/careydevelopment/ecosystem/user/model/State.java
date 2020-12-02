package com.careydevelopment.ecosystem.user.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@environment.getProperty('mongo.state.collection')}")
public class State {

	private String name;
	private String twoLetterCode;
	
	
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
	
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
