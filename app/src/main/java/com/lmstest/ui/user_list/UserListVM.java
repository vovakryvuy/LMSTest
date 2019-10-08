package com.lmstest.ui.user_list;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.lmstest.data.ManagerSharedPreferences;
import com.lmstest.data.view_model.UserModel;
import com.lmstest.network.ApiService;
import com.lmstest.network.response.log_out_response.LogOutResponse;
import com.lmstest.network.response.ErrorResponse;
import com.lmstest.network.response.list_users_response.BaseUserListResponse;
import com.lmstest.ui.base.BaseViewModel;

import java.net.HttpURLConnection;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lmstest.utils.Constant.LIMIT_COUNT_PAGINATION;
import static com.lmstest.utils.ParseUtils.parseUserList;

public class UserListVM extends BaseViewModel<UserListNavigator> {
	private MutableLiveData<List<UserModel>> userList = new MutableLiveData<>();
	private ManagerSharedPreferences managerSharedPreferences;
	private boolean isPaginationFinish = false;
	private int currentPage = 0;
	private Callback<LogOutResponse> logOutCallback = new Callback<LogOutResponse>() {
		@Override
		public void onResponse(Call<LogOutResponse> call, Response<LogOutResponse> response) {
			if (response.body() != null){
				checkLogOutResponse(response.body());
			}
		}

		@Override
		public void onFailure(Call<LogOutResponse> call, Throwable t) {
			loading.postValue(false);
		}
	};
	private Callback<BaseUserListResponse> getUserListCallback = new Callback<BaseUserListResponse>() {
		@Override
		public void onResponse(Call<BaseUserListResponse> call, Response<BaseUserListResponse> response) {
			if (response.body() != null){
				parseUserListResponse(response.body());
			}
		}

		@Override
		public void onFailure(Call<BaseUserListResponse> call, Throwable t) {
			loading.postValue(false);
		}
	};

	public UserListVM() {
		managerSharedPreferences = ManagerSharedPreferences.getInstance();
	}

	public MutableLiveData<List<UserModel>> getUserList() {
		return userList;
	}

	public void loadListUser() {
		String token = managerSharedPreferences.getToken();
		String accountId = managerSharedPreferences.getAccountId();
		if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(accountId)) {
			ApiService.getUsersList(accountId, currentPage, LIMIT_COUNT_PAGINATION, token, getUserListCallback);
		}
		currentPage++;
	}

	public void clickLogOut() {
		String token = managerSharedPreferences.getToken();
		if (!token.isEmpty()) {
			loading.postValue(true);
			ApiService.logout(token, logOutCallback);
		}
	}

	private void checkLogOutResponse(LogOutResponse response) {
		if (response.getCode() == HttpURLConnection.HTTP_OK) {
			loading.postValue(false);
			managerSharedPreferences.deleteAuthData();
			getNavigator().logOut();
		}
	}

	private void parseUserListResponse(BaseUserListResponse response) {
		if (response.getCode() == HttpURLConnection.HTTP_OK) {
			List<UserModel> userModelList = parseUserList(response);
			if (userModelList.isEmpty() || userModelList.size() < LIMIT_COUNT_PAGINATION){
				isPaginationFinish = true;
			}
			userList.postValue(userModelList);
			loading.postValue(false);
			return;
		}
		Gson gson = new Gson();
		ErrorResponse errorResponse = gson.fromJson((JsonElement) response.getResponse(),
				ErrorResponse.class);
		if (errorResponse != null && !TextUtils.isEmpty(errorResponse.getResponse().getMessage())){
			getNavigator().showErrorResponseMessage(errorResponse.getResponse().getMessage());
		}
		loading.postValue(false);
	}

	public boolean isPaginationFinish() {
		return isPaginationFinish;
	}
}
