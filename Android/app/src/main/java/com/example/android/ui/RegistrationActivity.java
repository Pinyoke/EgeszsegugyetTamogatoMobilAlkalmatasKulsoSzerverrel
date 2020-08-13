package com.example.android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.constant.Constans;
import com.example.android.model.User;
import com.example.android.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    UserService userService = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

       /* //Lszedi a felső bart
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/

        findViewById(R.id.click_to_singin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingIn();
            }


        });

        findViewById(R.id.btn_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });

    }

    private void registration() {
        User user = new User();
        EditText email = (EditText)findViewById(R.id.email);
        EditText name = (EditText)findViewById(R.id.name);
        EditText password = (EditText)findViewById(R.id.password);
        user.setEmail(email.toString());
        user.setName(name.toString());
        user.setPassword(password.toString());

        Call<User> userRegistration = userService.registration(user);

        userRegistration.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                openSingIn();

                if(response.isSuccessful()){
                    System.out.println("EZT AKAROM LÁTNI");
                }else{
                    System.out.println("EZT AKAROM LÁTNI");

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable error) {
                Toast.makeText(RegistrationActivity.this,"RegistrationError: "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openSingIn() {
        Intent registrationToLogin = new Intent(RegistrationActivity.this, LoginActivity.class);

        startActivity(registrationToLogin);
    }
}
