package com.Learnhub.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String matlocation = "mat-upload-dir";
	
	private String sublocation = "sub-upload-dir";

	public String getmatLocation() {
		return matlocation;
	}

	public void setmatLocation(String location) {
		this.matlocation = location;
	}

	public String getSublocation() {
		return sublocation;
	}

	public void setSublocation(String sublocation) {
		this.sublocation = sublocation;
	}

}
