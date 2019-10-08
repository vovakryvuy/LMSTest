package com.lmstest.network.response.login_response;

import com.google.gson.annotations.SerializedName;
import com.lmstest.network.response.login_response.GroupResponse;

import java.util.List;

public class UserResponse {
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private Object lastName;
	private Object title;
	@SerializedName("account_id")
	private String accountId;
	private String id;
	@SerializedName("_user_type")
	private String userType;
	private List<GroupResponse> groups = null;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Object getLastName() {
		return lastName;
	}

	public void setLastName(Object lastName) {
		this.lastName = lastName;
	}

	public Object getTitle() {
		return title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<GroupResponse> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupResponse> groups) {
		this.groups = groups;
	}
}
