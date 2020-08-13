package com.example.android.service;

import android.net.http.HttpResponseCache;

import com.example.android.model.Allergen;
import com.example.android.model.JwtRequest;
import com.example.android.model.JwtResponse;
import com.example.android.model.ScanResult;
import com.example.android.model.Sensitivity;
import com.example.android.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserService {

    @POST("authenticate")
    Call<JwtResponse> login(@Body JwtRequest jwtRequest);

    @POST("registration")
    Call<User> registration(@Body User user);

    @GET("profile")
    Call<User> getUserProfile(@Header("Authorization") String token);

    @GET("scanresult/{qrCode}")
    Call<ScanResult> getScanResult(@Header("Authorization") String token,@Path("qrCode") String qrCode);

    @POST("users/deletesensitivity/{id}")
    Call<List<Sensitivity>> deleteSensitivity(@Header("Authorization") String token, @Path("id") Long qrCode);

    @POST("user/mobil/modify")
    Call<User> modifyUser(@Header("Authorization") String token, @Body User user);


}
