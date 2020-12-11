package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;

import static com.example.firstassignment.Constants.SP_FILE;

public class WinnerActivity extends AppCompatActivity {

    private TextView LBL_winner;
    private ImageButton winner_BTN_newGame;
    private ImageButton winner_BTN_records;
    private TextView winner_LBL_newGame;
    private TextView winner_LBL_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner);

        findViews();

        Intent i = getIntent();
        Player winner = (Player)i.getSerializableExtra("theWinner");
        LBL_winner.setText(winner.getName() + " Wins with " + winner.getScore() + " points!");
        winner_BTN_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinnerActivity.this, MainActivity.class));
            }
        });
        winner_BTN_records.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinnerActivity.this, RecordsActivity.class));
            }
        });

        addWinnerToDb(winner.getName(), winner.getScore());

    }

    private void addWinnerToDb(String name, int score) {
        TopTen topTen = new TopTen();
        for (int i = 0; i < 10; i++) {
            Record record = new Record()
                    .setName(MySP.getInstance().getString("name"+i, ""))
                    .setScore(MySP.getInstance().getInt("score"+i, -1));
                    if (record.getScore() == -1) {
                        break;
                    }
            topTen.getRecords().add(record);
        }
        topTen.getRecords().add(new Record().setName(name).setScore(score));
        topTen.sortTopTen();
        int topTenSize = topTen.getRecords().size() > 10 ? 10 : topTen.getRecords().size();
        for (int i = 0; i < topTenSize; i++) {
            MySP.getInstance().putString("name"+i, topTen.getRecords().get(i).getName());
            MySP.getInstance().putInt("score"+i, topTen.getRecords().get(i).getScore());
        }
        Gson gson = new Gson();
        String topTenJson = gson.toJson(topTen);
        MySP.getInstance().putString("topTenJson", topTenJson);
    }

    private void findViews() {
        winner_BTN_newGame = findViewById(R.id.winner_BTN_newGame);
        winner_BTN_records = findViewById(R.id.winner_BTN_records);
        winner_LBL_newGame = findViewById(R.id.winner_LBL_newGame);
        winner_LBL_records = findViewById(R.id.winner_LBL_records);
        LBL_winner = findViewById(R.id.LBL_winner);
    }
}