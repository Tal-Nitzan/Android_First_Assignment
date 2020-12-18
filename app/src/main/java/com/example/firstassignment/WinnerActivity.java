package com.example.firstassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.google.gson.Gson;

public class WinnerActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    Double latitude, longitude;
    private TextView LBL_winner;
    private ImageButton winner_BTN_newGame;
    private ImageButton winner_BTN_records;
    private MediaPlayer mp;
    // random latitude and longitude values
//    private final double LAT_MIN_VAL = 32;
//    private final double LAT_MAX_VAL = 32.1;
//    private final double LON_MIN_VAL = 34.8;
//    private final double LON_MAX_VAL = 34.85;
    private Player winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner);
        findViews();
        startWinningSound();
        initViews();
        addWinnerToDb();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopWinningSound();
    }

    private void startWinningSound() {
        mp = MediaPlayer.create(WinnerActivity.this, R.raw.snd_winning);
        mp.start();
    }

    private void stopWinningSound() {
        mp.stop();
        mp.release();
    }

    private void addWinnerToDb() {
        //Generating random location every win.
//        Random r = new Random();
//        latitude = LAT_MIN_VAL + (LAT_MAX_VAL - LAT_MIN_VAL) * r.nextDouble();
//        longitude = LON_MIN_VAL + (LON_MAX_VAL - LON_MIN_VAL) * r.nextDouble();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation(); // Need to fix that so we update latitude and longitude here
        }

        String topTenString = MySP.getInstance().getString("topTenJson", "{}");
        Gson gson = new Gson();
        TopTen topTen = gson.fromJson(topTenString, TopTen.class);
        topTen.getRecords().add(new Record(winner.getName(), winner.getScore(), latitude, longitude));
        topTen.sortTopTen();
        int topTenSize = topTen.getRecords().size() > 10 ? 10 : topTen.getRecords().size();
        for (int i = 0; i < topTenSize; i++) {
            MySP.getInstance().putString("name" + i, topTen.getRecords().get(i).getName());
            MySP.getInstance().putInt("score" + i, topTen.getRecords().get(i).getScore());
            MySP.getInstance().putDouble("lat" + i, topTen.getRecords().get(i).getLat());
            MySP.getInstance().putDouble("lon" + i, topTen.getRecords().get(i).getLon());
        }
        String topTenJson = gson.toJson(topTen);
        MySP.getInstance().putString("topTenJson", topTenJson);
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                WinnerActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                WinnerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();
                        }

                        @Override
                        public void onProviderEnabled(@NonNull String provider) {

                        }

                        @Override
                        public void onProviderDisabled(@NonNull String provider) {

                        }
                    });
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                latitude = locationGPS.getLatitude();
                longitude = locationGPS.getLongitude();
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void findViews() {
        winner_BTN_newGame = findViewById(R.id.winner_BTN_newGame);
        winner_BTN_records = findViewById(R.id.winner_BTN_records);
        LBL_winner = findViewById(R.id.LBL_winner);
    }

    private void initViews() {
        Intent i = getIntent();
        winner = (Player) i.getSerializableExtra("theWinner");
        LBL_winner.setText(winner.getName() + " Wins with " + winner.getScore() + " points!");
        winner_BTN_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinnerActivity.this, MainActivity.class));
                finish();
            }
        });
        winner_BTN_records.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinnerActivity.this, RecordsActivity.class));
                finish();
            }
        });
    }

}