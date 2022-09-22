package com.fourrunstudios.nutechwallet.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://tht-api.nutech-integrasi.app/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final NutechAPI nutechAPI = retrofit.create(NutechAPI.class);

    public static NutechAPI getNutechAPI() {
        return nutechAPI;
    }
    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
