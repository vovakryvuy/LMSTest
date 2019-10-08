package com.lmstest.network.response;

import com.google.gson.JsonObject;

public class BaseResponse {
	private int code;
	private String status;
	private JsonObject response;

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

	public JsonObject getResponse() {
		return response;
	}

	public void setResponse(JsonObject response) {
		this.response = response;
	}
}
