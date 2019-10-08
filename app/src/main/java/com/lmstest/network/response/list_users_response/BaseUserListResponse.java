package com.lmstest.network.response.list_users_response;

import com.google.gson.JsonArray;

public class BaseUserListResponse {
	private int code;
	private String status;
	private JsonArray response;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JsonArray getResponse() {
		return response;
	}

	public void setResponse(JsonArray response) {
		this.response = response;
	}
}
