package com.lmstest.data.view_model;

import com.lmstest.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserModel extends BaseObservable {
	private String userName;
	private String fullName;
	private String group;

	@Bindable
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		notifyPropertyChanged(BR.userName);
	}

	@Bindable
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
		notifyPropertyChanged(BR.fullName);
	}

	@Bindable
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
		notifyPropertyChanged(BR.group);
	}
}
