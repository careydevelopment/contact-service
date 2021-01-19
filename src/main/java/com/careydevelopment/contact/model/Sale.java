package com.careydevelopment.contact.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Sale {

	private String purchaseOrderNumber;
	private String title;
	private Integer value;
	private Long date;
	private LineOfBusiness lineOfBusiness;
	
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	
	public LineOfBusiness getLineOfBusiness() {
		return lineOfBusiness;
	}
	public void setLineOfBusiness(LineOfBusiness lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
