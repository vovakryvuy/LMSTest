package com.lmstest.network.response.login_response;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
	private String token;
	private String seconds;
	private String userType;
	private UserResponse user;
	private String login;
	@SerializedName("api_user_id")
	private String apiUserId;
	private Integer accountAdmin;
	private String hash;
	private String account;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getApiUserId() {
		return apiUserId;
	}

	public void setApiUserId(String apiUserId) {
		this.apiUserId = apiUserId;
	}

	public Integer getAccountAdmin() {
		return accountAdmin;
	}

	public void setAccountAdmin(Integer accountAdmin) {
		this.accountAdmin = accountAdmin;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
