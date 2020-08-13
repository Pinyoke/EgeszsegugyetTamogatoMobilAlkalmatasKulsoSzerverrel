package com.example.android.service;

import com.example.android.model.Allergen;
import com.example.android.model.JwtRequest;
import com.example.android.model.JwtResponse;
import com.example.android.model.Sensitivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SensitivityService {

    @GET("getallergens")
    Call<List<Allergen>> getAllergen(@Header("Authorization") String token);

    @GET("sensitivitys")
    Call<List<Sensitivity>> getSensitivitys(@Header("Authorization") String token);

    @POST("sensitivitys/create")
    Call<Sensitivity> createSensitivity(@Header("Authorization") String token, @Body Sensitivity sensitivity);
}
