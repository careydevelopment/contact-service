package com.careydevelopment.ecosystem.user.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class FileUploadDetails {

	private long uploadFileSize;

	public long getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(long uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}
	
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
