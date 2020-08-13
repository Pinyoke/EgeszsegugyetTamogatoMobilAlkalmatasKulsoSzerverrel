package com.example.android.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.adapter.IngredientAdapter;
import com.example.android.adapter.OfferAdapter;
import com.example.android.constant.Constans;
import com.example.android.model.FoodIngredient;
import com.example.android.model.Offer;
import com.example.android.model.ScanResult;
import com.example.android.model.Sensitivity;
import com.example.android.service.UserService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class ResultActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constans.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    UserService userService = retrofit.create(UserService.class);

    private ImageView imageView;
    private TextView messageView;

    private RecyclerView offerList;
    private TextView ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setNavigationEventListener();
        Bundle result = getIntent().getExtras();
        if(result.getString("scanResult") != null) {
            getResult();
        }else{
            this.finish();
        }
    }

    private void getResult() {
        System.out.println("2");
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        Bundle result = getIntent().getExtras();
        String gtin13 = result.getString("scanResult");

        Call<ScanResult> getScanresult = userService.getScanResult(sharedPreferences.getString("token",null), gtin13);
        getScanresult.enqueue(new Callback<ScanResult>() {
            @Override
            public void onResponse(Call<ScanResult> call, Response<ScanResult> response) {
                    if(response.isSuccessful()){

                        Toast.makeText(ResultActivity.this,"EREDMENY: "+ response.body().getMessage()+"PRODUCT: "+response.body().getProduct().getName(), Toast.LENGTH_SHORT).show();
                        showResult(response.body());
                    }
            }



            @Override
            public void onFailure(Call<ScanResult> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ResultActivity.this,"LoginError:"+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showResult(ScanResult scanResult){
        //ImageView
        imageView = findViewById(R.id.resultImage);
        String url = Constans.IMAGE_URL + scanResult.getProduct().getImageUrl().substring(21);
        System.out.println(url);
        Picasso.get().load(url).into(imageView);
        //Message
        messageView = findViewById(R.id.resultMessage);
        messageView.setText(scanResult.getMessage());

        //OfferList
        offerList = (RecyclerView) findViewById(R.id.offerList);
        OfferAdapter adapter = new OfferAdapter(scanResult.getOffers());
        offerList.setHasFixedSize(true);
        offerList.setLayoutManager(new LinearLayoutManager(this));
        offerList.setAdapter(adapter);

        //IngredientListView:
       ingredientList = (TextView) findViewById(R.id.ingredientsList);
       String result = "";
        for (FoodIngredient foodIngredient: scanResult.getProduct().getFoodIngredients()) {
            result += foodIngredient.getName()+", ";
        }

       ingredientList.setText(result.substring(0,result.length()-2));


    }



    private void setNavigationEventListener() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigationResultId);

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
