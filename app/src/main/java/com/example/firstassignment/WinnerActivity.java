package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    private TextView LBL_winner;
    private ImageButton BTN_replay;

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
        BTN_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WinnerActivity.this, MainActivity.class));
            }
        });

    }


    private void findViews() {
        LBL_winner = findViewById(R.id.LBL_winner);
        BTN_replay = findViewById(R.id.BTN_replay);
    }
}