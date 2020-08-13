package com.example.android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.constant.Constans;
import com.example.android.model.JwtRequest;
import com.example.android.model.JwtResponse;
import com.example.android.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    UserService userService = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* //Lszedi a felső bart
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        */


        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        findViewById(R.id.click_to_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistration();
            }
        });
    }

    private void openRegistration() {
        Intent loginToRegistration = new Intent(LoginActivity.this, RegistrationActivity.class);

        startActivity(loginToRegistration);

    }

    private void login(){
        JwtRequest jwtRequest = new JwtRequest();
        EditText password = (EditText)findViewById(R.id.password);
        EditText username = (EditText)findViewById(R.id.email);
        jwtRequest.setPassword(password.getText().toString());
        jwtRequest.setUsername(username.getText().toString());

        Call<JwtResponse> userLogin = userService.login(jwtRequest);
        userLogin.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if(response.isSuccessful()) {
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("token", "Bearer " + response.headers().get("Authorization"));
                    editor.commit();


                    Intent loginToProfil = new Intent(LoginActivity.this, ProfilActivity.class);
                    startActivity(loginToProfil);
                }else {
                    Toast.makeText(LoginActivity.this,"Username or Password is not correct", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JwtResponse> call, Throwable error) {
                error.printStackTrace();
                Toast.makeText(LoginActivity.this,"LoginError:"+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
