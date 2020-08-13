package com.example.android.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.R;
import com.example.android.adapter.SensitivityAdapter;
import com.example.android.constant.Constans;
import com.example.android.model.Allergen;
import com.example.android.model.Sensitivity;
import com.example.android.model.User;
import com.example.android.service.SensitivityService;
import com.example.android.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilActivity extends AppCompatActivity {


    private RecyclerView sensitivityListView;
    private Toolbar toolbar;

    private EditText name;
    private EditText email;
    private EditText password;

    private User user;

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    SensitivityService sensitivityService= retrofit.create(SensitivityService.class);
    UserService userService = retrofit.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setToolbar();
        setNavigationEventListener();
        getUserDetails();
        getUserSensitivitys();
        setButtons();

    }

    private void setButtons() {
        findViewById(R.id.addSensitivityButtonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSensitivityCreatorView();
            }


        });
        findViewById(R.id.modifyProfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 password.setVisibility(View.VISIBLE);
                 findViewById(R.id.userProfilPassword).setVisibility(View.VISIBLE);
                 findViewById(R.id.saveProfil).setVisibility(View.VISIBLE);
                 findViewById(R.id.closeModify).setVisibility(View.VISIBLE);
                 findViewById(R.id.modifyProfil).setVisibility(View.INVISIBLE);

                 name.setEnabled(true);
                 email.setEnabled(true);
                 password.setEnabled(true);

            }


        });
        findViewById(R.id.saveProfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setVisibility(View.INVISIBLE);
                findViewById(R.id.userProfilPassword).setVisibility(View.INVISIBLE);
                findViewById(R.id.saveProfil).setVisibility(View.INVISIBLE);
                findViewById(R.id.closeModify).setVisibility(View.INVISIBLE);
                findViewById(R.id.modifyProfil).setVisibility(View.VISIBLE);
                name.setEnabled(false);
                email.setEnabled(false);
                password.setEnabled(false);
                modifyUser();


            }
        });
        findViewById(R.id.closeModify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setVisibility(View.INVISIBLE);
                findViewById(R.id.userProfilPassword).setVisibility(View.INVISIBLE);
                findViewById(R.id.saveProfil).setVisibility(View.INVISIBLE);
                findViewById(R.id.closeModify).setVisibility(View.INVISIBLE);
                findViewById(R.id.modifyProfil).setVisibility(View.VISIBLE);
                name.setEnabled(false);
                email.setEnabled(false);
                password.setEnabled(false);
            }
        });
    }

    private void modifyUser() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        if(!password.getText().toString().equals("")) {
            user.setPassword(password.getText().toString());
            System.out.println(user.getPassword());
        }else
        {
            System.out.println(user.getPassword());
        }
        Call<User> modifyuser = userService.modifyUser(sharedPreferences.getString("token",null),user);
        modifyuser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    System.out.println("Sikeres");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ProfilActivity.this,"LoginError:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void getUserDetails() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        Call<User> getUserDetails = userService.getUserProfile(sharedPreferences.getString("token",null));

        getUserDetails.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    createUserProfil(response.body());
                }
            }



            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ProfilActivity.this,"LoginError:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        ImageView button = findViewById(R.id.btn_logoutToolbar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("token");
                editor.commit();
                Intent profileToLogin = new Intent(ProfilActivity.this, LoginActivity.class);
                startActivity(profileToLogin);
            }
        });
        setSupportActionBar(toolbar);
    }

    private void createUserProfil(User body) {
        user = body;
        name =(EditText) findViewById(R.id.userProfilNameText);
        email =(EditText) findViewById(R.id.userProfilNameEmail);
        password =(EditText) findViewById(R.id.userProfilNamePassword);
        name.setText(body.getName());
        email.setText(body.getEmail());
        name.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);

    }

    private void getUserSensitivitys() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        Call<List<Sensitivity>> getSensitivitys = sensitivityService.getSensitivitys(sharedPreferences.getString("token",null));

        getSensitivitys.enqueue(new Callback<List<Sensitivity>>() {
            @Override
            public void onResponse(Call<List<Sensitivity>> call, Response<List<Sensitivity>> response) {
                if(response.isSuccessful()) {

                            createListView(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Sensitivity>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ProfilActivity.this,"LoginError:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createListView(List<Sensitivity> sensitivities) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        sensitivityListView = findViewById(R.id.sensitivityList);
        SensitivityAdapter adapter = new SensitivityAdapter(sensitivities,sharedPreferences,this);
        sensitivityListView.setHasFixedSize(true);
        sensitivityListView.setLayoutManager(new LinearLayoutManager(this));
        sensitivityListView.setAdapter(adapter);
    }

    public void deleteSensitivity(Long id){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        Call<List<Sensitivity>> deleteSensitivity = userService.deleteSensitivity(sharedPreferences.getString("token",null), id);
        deleteSensitivity.enqueue(new Callback<List<Sensitivity>>() {
            @Override
            public void onResponse(Call<List<Sensitivity>> call, Response<List<Sensitivity>> response) {
                if(response.isSuccessful()){
                    System.out.println("A TÖRLÉS SIKERES");
                    getUserSensitivitys();
                }
            }

            @Override
            public void onFailure(Call<List<Sensitivity>> call, Throwable t) {
                System.out.println("Sikertelen törlés");
            }
        });




    }


    private void openSensitivityCreatorView() {

        Intent profileToCreator = new Intent(ProfilActivity.this, CreatSensitivityActivity.class);
        startActivity(profileToCreator);
    }

    private void setNavigationEventListener() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigationProfilId);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigationProfilId:
                        startActivity(new Intent(getApplicationContext(),ProfilActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.navigationResultId:
                        startActivity(new Intent(getApplicationContext(),ResultActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.navigationScanId:
                        startActivity(new Intent(getApplicationContext(),BarCodeActivity.class));

                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }

    }
