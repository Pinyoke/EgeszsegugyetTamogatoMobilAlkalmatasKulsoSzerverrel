package com.example.android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.constant.Constans;
import com.example.android.model.Allergen;
import com.example.android.model.Sensitivity;
import com.example.android.service.SensitivityService;
import com.example.android.service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatSensitivityActivity extends AppCompatActivity {

    private Spinner allergenSpinner;
    private Spinner typeSpinner;

    private List<Allergen> allergens = new ArrayList<>();
    private ArrayAdapter<Allergen> allergenArrayAdapter;

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    SensitivityService sensitivityService= retrofit.create(SensitivityService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_sensitivity);
        addItemsToSpinner();
        addButtonOnClickListener();
    }

    private void addButtonOnClickListener() {
        findViewById(R.id.addSensitivityButtonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createSensitivity();
            }


        });

    }

    private void createSensitivity() {
        String type = (String) typeSpinner.getSelectedItem();
        Allergen allergen = (Allergen) allergenSpinner.getSelectedItem();
        String description = ((EditText)findViewById(R.id.description)).getText().toString();

        System.out.println("Type: "+ type);
        System.out.println("AllergenName: "+ allergen.getName());
        System.out.println("Desc? : "+ description);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);

        Sensitivity sensitivity = new Sensitivity();
        sensitivity.setAllergen(allergen);
        sensitivity.setDescription(description);
        sensitivity.setMyType(type);
        Call<Sensitivity> createSensitivity = sensitivityService.createSensitivity(sharedPreferences.getString("token",null), sensitivity );

        createSensitivity.enqueue(new Callback<Sensitivity>() {
            @Override
            public void onResponse(Call<Sensitivity> call, Response<Sensitivity> response) {
                if(response.isSuccessful()){
                        openProfilActivity();

                }
            }

            @Override
            public void onFailure(Call<Sensitivity> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(CreatSensitivityActivity.this,"LoginError:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void openProfilActivity() {
        Intent createToProfil = new Intent(CreatSensitivityActivity.this, ProfilActivity.class);
        startActivity(createToProfil);

    }

    private void addItemsToSpinner() {
        allergenSpinner = (Spinner)findViewById(R.id.allergensSpinnerId);
        typeSpinner = (Spinner)findViewById(R.id.typeSpinnerId);


        List<String> types = new ArrayList<>();
        types.add("Fogyasztása tilos");
        types.add("Fogyasztása nem ajánlott");

        ArrayAdapter<String> typeDataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, types);
        typeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeDataAdapter);


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        Call<List<Allergen>> getAllergens = sensitivityService.getAllergen(sharedPreferences.getString("token",null));


        getAllergens.enqueue(new Callback<List<Allergen>>() {
            @Override
            public void onResponse(Call<List<Allergen>> call, Response<List<Allergen>> response) {
                    if(response.isSuccessful()){



                        for (Allergen allergen: response.body()) {
                            allergens.add(allergen);

                        }

                         createSpinner();
                    }
            }
            @Override
            public void onFailure(Call<List<Allergen>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(CreatSensitivityActivity.this,"LoginError:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void createSpinner()
    {
        for (Allergen allergen: allergens) {
            System.out.println("Allergen: "+allergen.getName());

        }
        allergenArrayAdapter = new ArrayAdapter<Allergen>(this,android.R.layout.simple_spinner_dropdown_item, allergens);
        allergenArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allergenArrayAdapter.setNotifyOnChange(true);
        allergenSpinner.setAdapter(allergenArrayAdapter);
    };
}
