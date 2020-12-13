package com.example.firstassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import im.delight.android.location.SimpleLocation;

import static com.example.firstassignment.Constants.SP_FILE;

public class WinnerActivity extends AppCompatActivity {

    private TextView LBL_winner;
    private ImageButton winner_BTN_newGame;
    private ImageButton winner_BTN_records;
    private TextView winner_LBL_newGame;
    private TextView winner_LBL_records;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner);

        findViews();

        mp = MediaPlayer.create(WinnerActivity.this, R.raw.snd_winning);
        mp.start();

        Intent i = getIntent();
        Player winner = (Player)i.getSerializableExtra("theWinner");
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
        Random r = new Random();
        double randomLat = 32 + (32.1 - 32) * r.nextDouble();
        double randomLon = 34.8 + (34.85 - 34.8) * r.nextDouble();
        addWinnerToDb(winner.getName(), winner.getScore(), randomLat, randomLon);

    }

    private void addWinnerToDb(String name, int score, double lat, double lon) {
        String topTenString = MySP.getInstance().getString("topTenJson", "{}");
        Gson gson = new Gson();
        TopTen topTen = gson.fromJson(topTenString, TopTen.class);
        topTen.getRecords().add(new Record(name, score, lat, lon));
        topTen.sortTopTen();
        int topTenSize = topTen.getRecords().size() > 10 ? 10 : topTen.getRecords().size();
        for (int i = 0; i < topTenSize; i++) {
            MySP.getInstance().putString("name"+i, topTen.getRecords().get(i).getName());
            MySP.getInstance().putInt("score"+i, topTen.getRecords().get(i).getScore());
            MySP.getInstance().putDouble("lat"+i, topTen.getRecords().get(i).getLat());
            MySP.getInstance().putDouble("lon"+i, topTen.getRecords().get(i).getLon());
        }
        String topTenJson = gson.toJson(topTen);
        Log.d("zzzz", topTenJson);
        MySP.getInstance().putString("topTenJson", topTenJson);
    }

    private void findViews() {
        winner_BTN_newGame = findViewById(R.id.winner_BTN_newGame);
        winner_BTN_records = findViewById(R.id.winner_BTN_records);
        winner_LBL_newGame = findViewById(R.id.winner_LBL_newGame);
        winner_LBL_records = findViewById(R.id.winner_LBL_records);
        LBL_winner = findViewById(R.id.LBL_winner);
    }

    @Override
    protected void onPause() {
        mp.stop();
        mp.release();
        super.onPause();

    }
}