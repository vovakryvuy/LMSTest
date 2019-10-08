package com.lmstest.network;

import com.lmstest.network.log_out_response.LogOutResponse;
import com.lmstest.network.response.BaseResponse;
import com.lmstest.network.response.list_users_response.BaseUserListResponse;

import retrofit2.Callback;

import static com.lmstest.utils.Constant.EXTEND;
import static com.lmstest.utils.Constant.USER_TYPE;

public class ApiService {
	private static RetrofitAdapter retrofitAdapter = RetrofitAdapter.getInstance();

	public static void login(String userName,
							 String password,
							 Callback<BaseResponse> callback) {
		retrofitAdapter.getApi().login(userName, password, USER_TYPE, EXTEND).enqueue(callback);
	}

	public static void getUsersList(String accountId,
									int start,
									int limit,
									String token,
									Callback<BaseUserListResponse> callback) {
		retrofitAdapter.getApi().getUserList(accountId, start, limit, token).enqueue(callback);
	}

	public static void logout(String token,
							  Callback<LogOutResponse> callback) {
		retrofitAdapter.getApi().logout(token).enqueue(callback);
	}
}
