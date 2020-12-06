package com.careydevelopment.contact.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@environment.getProperty('mongo.contact.collection')}")
public class Contact {

	@Id
	private String id;
	
	@NotBlank(message = "Please provide a first name")
	@Size(max = 50, message = "First name must be between 1 and 50 characters")
	private String firstName;
	
	@NotBlank(message = "Please provide a last name")
	@Size(max = 50, message = "Last name must be between 1 and 50 characters")
	private String lastName;
	
	@Email(message = "Please enter a valid email address")
	private String email;
	private List<Phone> phones = new ArrayList<Phone>();
	private List<Address> addresses = new ArrayList<Address>();
	
	private Source source;
	
	@Size(max = 50, message = "Source details cannot exceed 50 characters")
	private String sourceDetails;
	private WebsiteForm websiteFormSource;
	
	private ContactStatus status;
	private long statusChange;
	
	@NotEmpty(message = "Please include at least one line of business")
	private List<LineOfBusiness> linesOfBusiness;
	
	@Size(max = 50, message = "Company name cannot exceed 50 characters")
	private String company;
	
	@Size(max = 50, message = "Title cannot exceed 50 characters")
	private String title;
	private boolean authority;
	private SalesOwner salesOwner;
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	public List<Phone> getPhones() {
		return phones;
	}



	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}



	public List<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}



	public Source getSource() {
		return source;
	}



	public void setSource(Source source) {
		this.source = source;
	}


	public String getSourceDetails() {
		return sourceDetails;
	}


	public void setSourceDetails(String sourceDetails) {
		this.sourceDetails = sourceDetails;
	}


	public WebsiteForm getWebsiteFormSource() {
		return websiteFormSource;
	}



	public void setWebsiteFormSource(WebsiteForm websiteFormSource) {
		this.websiteFormSource = websiteFormSource;
	}

	

	public ContactStatus getStatus() {
		return status;
	}



	public void setContactStatus(ContactStatus status) {
		this.status = status;
	}

	

	public List<LineOfBusiness> getLinesOfBusiness() {
		return linesOfBusiness;
	}



	public void setLinesOfBusiness(List<LineOfBusiness> linesOfBusiness) {
		this.linesOfBusiness = linesOfBusiness;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public boolean isAuthority() {
		return authority;
	}



	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	

	public SalesOwner getSalesOwner() {
		return salesOwner;
	}



	public void setSalesOwner(SalesOwner salesOwner) {
		this.salesOwner = salesOwner;
	}


	public long getStatusChange() {
		return statusChange;
	}



	public void setStatusChange(long statusChange) {
		this.statusChange = statusChange;
	}



	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
