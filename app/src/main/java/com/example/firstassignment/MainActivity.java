package com.example.firstassignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    final int DELAY = 200; // 1500ms = 1.5 second
    private ImageButton BTN_nextDraw;
    private TextView LBL_leftScore;
    private TextView LBL_rightScore;
    private ImageView IMG_leftCard;
    private ImageView IMG_rightCard;
    private TextView LBL_leftPlayer;
    private TextView LBL_rightPlayer;
    private TextView LBL_cardsRemaining;
    private Game game;
    ProgressBar main_PRGBAR_nextDraw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        game = new Game();
        findViews();
        initViews();
    }

    private void initViews() {
        LBL_cardsRemaining.setText("Cards left: " + (Deck.TOTAL_NUM_CARDS));
        LBL_leftScore.setText("" + game.getP1().getScore());
        LBL_rightScore.setText("" + game.getP2().getScore());
        LBL_leftPlayer.setText(game.getP1().getName());
        LBL_rightPlayer.setText(game.getP2().getName());
        BTN_nextDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAutomatedGame();
                BTN_nextDraw.setVisibility(View.GONE);
            }
        });
    }

    private void openWinnerActivity(Player winner) {
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra("theWinner", winner);
        startActivity(intent);
        finish();
    }

    private void findViews() {
        BTN_nextDraw = findViewById(R.id.BTN_nextDraw);
        LBL_leftScore = findViewById(R.id.LBL_leftScore);
        LBL_rightScore = findViewById(R.id.LBL_rightScore);
        IMG_leftCard = findViewById(R.id.IMG_leftCard);
        IMG_rightCard = findViewById(R.id.IMG_rightCard);
        LBL_leftPlayer = findViewById(R.id.LBL_leftPlayer);
        LBL_rightPlayer = findViewById(R.id.LBL_rightPlayer);
        LBL_cardsRemaining = findViewById(R.id.LBL_cardsRemaining);
        main_PRGBAR_nextDraw = findViewById(R.id.main_PRGBAR_nextDraw);
    }

    private void startAutomatedGame() {
        Timer carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        game.makeStep();
                        int id1 = getResources().getIdentifier(String.format("card_%s_%c", game.getP1().getCurrentCard().getValue(), game.getP1().getCurrentCard().getSign()), "drawable", getPackageName());
                        int id2 = getResources().getIdentifier(String.format("card_%s_%c", game.getP2().getCurrentCard().getValue() , game.getP2().getCurrentCard().getSign()), "drawable", getPackageName());
                        Glide.with(getApplicationContext()).load(id1).placeholder(id1).into(IMG_leftCard);
                        Glide.with(getApplicationContext()).load(id2).placeholder(id2).into(IMG_rightCard);
                        LBL_leftScore.setText("" + game.getP1().getScore());
                        LBL_rightScore.setText("" + game.getP2().getScore());
                        LBL_cardsRemaining.setText("Cards left: " + ((Deck.TOTAL_NUM_CARDS)-game.getDeck().getDrawedCards()));
                        if (game.isDone()) {
                            openWinnerActivity(game.getWinner());
                        }
                        main_PRGBAR_nextDraw.incrementProgressBy(1);
                    }
                });
            }
        }, 0, DELAY);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}