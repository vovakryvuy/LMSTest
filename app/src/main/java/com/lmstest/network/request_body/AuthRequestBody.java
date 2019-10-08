package com.lmstest.network.request_body;

public class AuthRequestBody {
	private String username;
	private String password;
	private String userType;
	private String _extend;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String get_extend() {
		return _extend;
	}

	public void set_extend(String _extend) {
		this._extend = _extend;
	}
}
