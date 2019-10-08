package com.lmstest.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.lmstest.data.view_model.UserModel;
import com.lmstest.network.response.list_users_response.BaseUserListResponse;
import com.lmstest.network.response.list_users_response.UserDataResponse;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {
	public static List<UserModel> parseUserList(BaseUserListResponse response) {
		JsonArray jsonArray = response.getResponse();
		List<UserModel> userModelList = new ArrayList<>();
		Gson gson = new Gson();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonElement jsonElement = jsonArray.get(i);
			if (jsonElement != null) {
				UserDataResponse userDataResponse =
						gson.fromJson(jsonElement, UserDataResponse.class);
				userModelList.add(createUserModel(userDataResponse));
			}
		}
		return userModelList;
	}

	private static UserModel createUserModel(UserDataResponse dataResponse) {
		UserModel userModel = new UserModel();
		if (dataResponse != null) {
			if (dataResponse.getUsername() != null){
				userModel.setUserName(dataResponse.getUsername());
			}
			if (dataResponse.getFirstName() != null && dataResponse.getLastName() != null) {
				String fullName = String.format("%s %s", dataResponse.getFirstName(), dataResponse.getLastName());
				userModel.setFullName(fullName);
			}
			if (dataResponse.getGroup() != null){
				userModel.setGroup(dataResponse.getGroup());
			}
		}
		return userModel;
	}

}
