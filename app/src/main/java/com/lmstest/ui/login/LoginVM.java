package com.lmstest.ui.login;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.lmstest.R;
import com.lmstest.data.ManagerSharedPreferences;
import com.lmstest.network.ApiService;
import com.lmstest.network.response.BaseResponse;
import com.lmstest.network.response.ErrorResponse;
import com.lmstest.network.response.login_response.AuthResponse;
import com.lmstest.ui.base.BaseViewModel;

import java.net.HttpURLConnection;

import androidx.databinding.ObservableBoolean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginVM extends BaseViewModel<LoginNavigator> {
	public ObservableBoolean isRememberMe = new ObservableBoolean(false);
	public ObservableBoolean isSelectNameField = new ObservableBoolean(false);
	public ObservableBoolean isSelectPasswordField = new ObservableBoolean(false);
	private ManagerSharedPreferences managerSharedPreferences;
	private Callback<BaseResponse> loginCallback = new Callback<BaseResponse>() {
		@Override
		public void onResponse(Call<BaseResponse> call,
							   Response<BaseResponse> response) {
			if (response.body() != null){
				parseAuthData(response.body());
			}else {
				getNavigator().showErrorResponseMessage(response.message());
			}
			loading.postValue(false);
		}

		@Override
		public void onFailure(Call<BaseResponse> call, Throwable t) {
			loading.postValue(false);
		}
	};

	public LoginVM() {
		managerSharedPreferences = ManagerSharedPreferences.getInstance();
		isRememberMe.set(managerSharedPreferences.getRememberMe());
	}

	public void checkEnterData(String userName, String password) {
		if (TextUtils.isEmpty(userName)) {
			getNavigator().showWarningMessage(R.string.username_is_required);
			return;
		}
		if (TextUtils.isEmpty(password)) {
			getNavigator().showWarningMessage(R.string.password_is_required);
			return;
		}
		loading.postValue(true);
		ApiService.login(userName, password, loginCallback);
	}

	private void parseAuthData(BaseResponse response) {
		Gson gson = new Gson();
		if (response.getCode() == HttpURLConnection.HTTP_OK) {
			AuthResponse authResponse = gson.fromJson((JsonElement) response.getResponse(),
					AuthResponse.class);
			managerSharedPreferences.saveToke(authResponse.getToken());
			managerSharedPreferences.saveAccountId(authResponse.getUser().getAccountId());
			getNavigator().successLogin();
			return;
		}
		ErrorResponse errorResponse = gson.fromJson((JsonElement) response.getResponse(),
				ErrorResponse.class);
		if (errorResponse != null && !TextUtils.isEmpty(errorResponse.getResponse().getMessage())){
			getNavigator().showErrorResponseMessage(errorResponse.getResponse().getMessage());
		}
		loading.postValue(false);
	}

	public void changeCheckRememberMe(boolean isChecked) {
		managerSharedPreferences.saveIsRemeberMe(isChecked);
		isRememberMe.set(isChecked);
	}

	public void clickLogIn() {
		getNavigator().clickLogin();
	}
}
