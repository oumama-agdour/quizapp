package com.example.quizapp_agdour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;

import java.util.ArrayList;
import java.util.List;

public class score extends AppCompatActivity {
    Button bLogout, bTry, bLocation;
    ProgressBar progressBar;
    TextView tvScore;
    int score;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    double[] latLng = new double[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tvScore =(TextView) findViewById(R.id.tvScore);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        bLogout=(Button) findViewById(R.id.Logout);
        bTry=(Button) findViewById(R.id.Try);
        bLocation = findViewById(R.id.btLocation);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        progressBar.setProgress(100*score/5);
        tvScore.setText(100*score/5+" %");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    latLng[0]= location.getLatitude();
                    latLng[1] = (location.getLongitude());
                }
            }
        });


        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Merci de votre Participation !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        bTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(score.this,Qst1.class));
            }
        });

        bLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MapsActivity.class);
                intent1.putExtra("location", latLng);
                startActivity(intent1);
            }
        });


    }
}

