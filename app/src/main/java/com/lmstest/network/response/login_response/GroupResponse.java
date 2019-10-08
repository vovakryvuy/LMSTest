package com.lmstest.network.response.login_response;

import com.google.gson.annotations.SerializedName;

public class GroupResponse {
	private String id;
	private String code;
	private String title;
	@SerializedName("Record_Creation_Date")
	private String recordCreationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRecordCreationDate() {
		return recordCreationDate;
	}

	public void setRecordCreationDate(String recordCreationDate) {
		this.recordCreationDate = recordCreationDate;
	}
}
