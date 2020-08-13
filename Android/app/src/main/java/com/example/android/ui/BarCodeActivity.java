package com.example.android.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BarCodeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton openCamera;
    private ImageButton searchProduct;
    private EditText barCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
        setNavigationEventListener();
        setBarCode();
        setButtons();
    }

    private void setButtons() {
        openCamera = findViewById(R.id.openCameraButton);
        searchProduct = findViewById(R.id.searchBarCodeButton);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inent = new Intent(BarCodeActivity.this, ScanActivity.class);
                startActivity(inent);
            }
        });

        searchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barCode = findViewById(R.id.barcode);
                final String scanResult = barCode.getText().toString();
                Intent scanToResult = new Intent( BarCodeActivity.this , ResultActivity.class);
                scanToResult.putExtra("scanResult", scanResult);
                startActivity(scanToResult);

            }
        });
    }

    private void setBarCode() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
