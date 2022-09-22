package com.fourrunstudios.nutechwallet.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NutechAPI {
    @FormUrlEncoded
    @POST("registration")
    Call<APIResponse> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name);

    @FormUrlEncoded
    @POST("login")
    Call<APIResponse> login(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("updateProfile")
    Call<APIResponse> updateProfile(
            @Header("Authorization") String header,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name);

    @GET("getProfile")
    Call<APIResponse> getProfile(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("topup")
    Call<APIResponse> topUp(
            @Header("Authorization") String token,
            @Field("amount") int amount);

    @FormUrlEncoded
    @POST("transfer")
    Call<APIResponse> transfer(
            @Header("Authorization") String token,
            @Field("amount") int amount);

    @GET("transactionHistory")
    Call<APIResponse> getHistory(@Header("Authorization") String token);

    @GET("balance")
    Call<APIResponse> getBalance(@Header("Authorization") String token);
}
