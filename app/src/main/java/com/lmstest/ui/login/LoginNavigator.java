package com.lmstest.ui.login;

public interface LoginNavigator {
	void clickLogin();

	void showWarningMessage(int errorMessageRes);

	void showErrorResponseMessage(String errorResponseMessage);

	void successLogin();
}
