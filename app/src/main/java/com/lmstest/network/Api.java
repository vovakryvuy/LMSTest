package com.lmstest.network;

import com.lmstest.network.response.BaseResponse;
import com.lmstest.network.response.list_users_response.BaseUserListResponse;
import com.lmstest.network.response.log_out_response.LogOutResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
	@POST("auth")
	Call<BaseResponse> login(@Query("username") String userName,
							 @Query("password") String password,
							 @Query("usertype") String userType,
							 @Query("_extend") String extend);

	@GET("accounts/{accountId}/students")
	Call<BaseUserListResponse> getUserList(@Path("accountId") String accountId,
										   @Query("_start") Integer start,
										   @Query("_limit") Integer limit,
										   @Query("token") String token);


	@DELETE("auth")
	Call<LogOutResponse> logout(@Query("token") String token);

}
