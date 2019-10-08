package com.lmstest.network.response.list_users_response;

import com.google.gson.annotations.SerializedName;

public class UserDataResponse {
	private String username;
	@SerializedName("group_id")
	private String groupId;
	private String group;
	private String id;
	@SerializedName("account_id")
	private String accountId;
	@SerializedName("api_users_id")
	private String apiUsersId;
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	@SerializedName("email")
	private String email;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getApiUsersId() {
		return apiUsersId;
	}

	public void setApiUsersId(String apiUsersId) {
		this.apiUsersId = apiUsersId;
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
}
