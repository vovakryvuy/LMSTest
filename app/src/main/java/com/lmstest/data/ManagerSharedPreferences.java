package com.lmstest.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.lmstest.LMSApplication;


public class ManagerSharedPreferences {
	private static final String NAME_SHARED_PREFERENCES = "LMS_APPLICATION_SHARED_PREFERENCES";
	private static final String KEY_TOKEN = "KEY_TOKEN";
	private static final String KEY_ACCOUNT_ID = "KEY_ACCOUNT_ID";
	private static final String KEY_REMEMBER_ME = "KEY_REMEMBER_ME";
	private static ManagerSharedPreferences instance;
	private final SharedPreferences sharedPreferences;

	private ManagerSharedPreferences(Context context){
		sharedPreferences = context.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
	}


	public static ManagerSharedPreferences getInstance() {
		if (instance == null) {
			instance = new ManagerSharedPreferences(LMSApplication.getContext());
		}
		return instance;
	}

	public void saveToke(String companyName) {
		saveInSharedPreferences(companyName, KEY_TOKEN);
	}

	public String getToken() {
		if (sharedPreferences != null) {
			return sharedPreferences.getString(KEY_TOKEN, "");
		}
		return "";
	}

	public void saveAccountId(String accountId) {
		saveInSharedPreferences(accountId, KEY_ACCOUNT_ID);
	}

	public String getAccountId() {
		if (sharedPreferences != null) {
			return sharedPreferences.getString(KEY_ACCOUNT_ID, "");
		}
		return "";
	}

	public void saveIsRememberMe(boolean isRememberMe) {
		saveInSharedPreferences(isRememberMe, KEY_REMEMBER_ME);
	}

	public boolean getRememberMe() {
		if (sharedPreferences != null) {
			return sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
		}
		return false;
	}

	public void deleteAuthData(){
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(KEY_TOKEN, "");
		editor.putString(KEY_ACCOUNT_ID, "");
		editor.apply();
	}

	private void saveInSharedPreferences(boolean data, String key) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, data);
		editor.apply();
	}

	private void saveInSharedPreferences(String data, String key) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, data);
		editor.apply();
	}
}
