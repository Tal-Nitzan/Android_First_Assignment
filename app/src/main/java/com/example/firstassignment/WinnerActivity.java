package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;
import com.google.gson.Gson;

public class WinnerActivity extends AppCompatActivity {

    private TextView LBL_winner;
    private ImageButton winner_BTN_newGame;
    private ImageButton winner_BTN_records;
    private MediaPlayer mp;
    private final double LAT_MIN_VAL = 32;
    private final double LAT_MAX_VAL = 32.1;
    private final double LON_MIN_VAL = 34.8;
    private final double LON_MAX_VAL = 34.85;
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
        // Got no GPS using android emulator.
        Random r = new Random();
        double randomLat = LAT_MIN_VAL + (LAT_MAX_VAL - LAT_MIN_VAL) * r.nextDouble();
        double randomLon = LON_MIN_VAL + (LON_MAX_VAL - LON_MIN_VAL) * r.nextDouble();

        String topTenString = MySP.getInstance().getString("topTenJson", "{}");
        Gson gson = new Gson();
        TopTen topTen = gson.fromJson(topTenString, TopTen.class);
        topTen.getRecords().add(new Record(winner.getName(), winner.getScore(), randomLat, randomLon));
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