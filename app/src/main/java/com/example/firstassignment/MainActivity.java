//Code has been reviewed, Assignment 1 is ready
package com.example.firstassignment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    final int DELAY = 200; // 1500ms = 1.5 second
    private ImageButton main_BTN_play;
    private TextView main_LBL_leftScore;
    private TextView main_LBL_rightScore;
    private ImageView main_IMG_leftCard;
    private ImageView main_IMG_rightCard;
    private TextView main_LBL_leftPlayer;
    private TextView main_LBL_rightPlayer;
    private ImageView main_IMG_leftPlayer;
    private ImageView main_IMG_rightPlayer;
    private TextView main_LBL_cardsRemaining;
    private Game game;
    private ProgressBar main_PRGBAR_nextDraw;
    private MediaPlayer mp;
    private Timer carousalTimer;
    private boolean startGamePressed = false;


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

    @Override
    protected void onStart() { //
        super.onStart();
        if (startGamePressed) {
            startAutomatedGame();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAutomatedGame();
    }

    private void findViews() {
        main_BTN_play = findViewById(R.id.main_BTN_play);
        main_LBL_leftScore = findViewById(R.id.main_LBL_leftScore);
        main_LBL_rightScore = findViewById(R.id.main_LBL_rightScore);
        main_IMG_leftCard = findViewById(R.id.main_IMG_leftCard);
        main_IMG_rightCard = findViewById(R.id.main_IMG_rightCard);
        main_LBL_leftPlayer = findViewById(R.id.main_LBL_leftPlayer);
        main_LBL_rightPlayer = findViewById(R.id.main_LBL_rightPlayer);
        main_IMG_leftPlayer = findViewById(R.id.main_IMG_player1);
        main_IMG_rightPlayer = findViewById(R.id.main_IMG_player2);
        main_LBL_cardsRemaining = findViewById(R.id.main_LBL_cardsRemaining);
        main_PRGBAR_nextDraw = findViewById(R.id.main_PRGBAR_nextDraw);
    }

    private void initViews() {
        main_LBL_cardsRemaining.setText("Cards left: " + (Deck.TOTAL_NUM_CARDS));
        main_LBL_leftScore.setText("" + game.getP1().getScore());
        main_LBL_rightScore.setText("" + game.getP2().getScore());
        main_LBL_leftPlayer.setText(game.getP1().getName());
        Glide.with(getApplicationContext()).load(getResources().getIdentifier("player_1","drawable",getPackageName())).into(main_IMG_leftPlayer);
        main_LBL_rightPlayer.setText(game.getP2().getName());
        Glide.with(getApplicationContext()).load(getResources().getIdentifier("player_2","drawable",getPackageName())).into(main_IMG_rightPlayer);
        Glide.with(getApplicationContext()).load(getResources().getIdentifier("play_button", "drawable", getPackageName())).into(main_BTN_play);
        main_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGamePressed = true;
                startAutomatedGame();
                main_BTN_play.setVisibility(View.GONE);
            }
        });
    }


    private void startAutomatedGame() {
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mp = MediaPlayer.create(MainActivity.this, R.raw.snd_card);
                        mp.start();
                        game.makeStep();
                        int id1 = getResources().getIdentifier(String.format("card_%s_%c", game.getP1().getCurrentCard().getValue(), game.getP1().getCurrentCard().getSign()), "drawable", getPackageName());
                        int id2 = getResources().getIdentifier(String.format("card_%s_%c", game.getP2().getCurrentCard().getValue() , game.getP2().getCurrentCard().getSign()), "drawable", getPackageName());
                        Glide.with(getApplicationContext()).load(id1).placeholder(id1).into(main_IMG_leftCard);
                        Glide.with(getApplicationContext()).load(id2).placeholder(id2).into(main_IMG_rightCard);
                        main_LBL_leftScore.setText("" + game.getP1().getScore());
                        main_LBL_rightScore.setText("" + game.getP2().getScore());
                        main_LBL_cardsRemaining.setText("Cards left: " + ((Deck.TOTAL_NUM_CARDS)-game.getDeck().getDrawedCards()));
                        if (game.isDone()) {
                            startGamePressed = false;
                            stopAutomatedGame();
                            openWinnerActivity(game.getWinner());
                        }
                        main_PRGBAR_nextDraw.incrementProgressBy(1);
                    }
                });
            }
        }, 0, DELAY);
    }

    private void stopAutomatedGame() {
        carousalTimer.cancel();
    }


    private void openWinnerActivity(Player winner) {
        Intent intent = new Intent(MainActivity.this, WinnerActivity.class);
        intent.putExtra("theWinner", winner);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}