package com.lmstest.network;

import com.lmstest.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lmstest.utils.Constant.TIME_OUT_CONNECTION;

public class RetrofitAdapter {
	private static RetrofitAdapter instance;
	private static Api api;

	private RetrofitAdapter() {
		api = provideRestService();
	}

	public static RetrofitAdapter getInstance() {
		if (instance == null)
			instance = new RetrofitAdapter();
		return instance;
	}

	private Api provideRestService() {
		return new Retrofit.Builder()
				.client(getHttpClient())
				.baseUrl(BuildConfig.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build().create(Api.class);
	}

	private OkHttpClient getHttpClient() {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
				.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
				.connectTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
				.readTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
				.writeTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS);

		return httpClient.build();
	}

	public Api getApi() {
		return api;
	}

}
