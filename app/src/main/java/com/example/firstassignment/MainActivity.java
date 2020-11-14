package com.example.firstassignment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private ImageButton BTN_nextDraw;
    private TextView LBL_leftScore;
    private TextView LBL_rightScore;
    private TextView LBL_winner;
    ImageView IMG_leftCard;
    ImageView IMG_rightCard;
    Deck deck;
    Player p1;
    Player p2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        p1 = new Player();
        p2 = new Player();
        deck = new Deck();
        findViews();
        initViews();
    }

    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        Log.d("pttt", "Timer Started");
        super.onStart();
    }

    private void initViews() {
        LBL_leftScore.setText("" + p1.getScore());
        LBL_rightScore.setText("" + p2.getScore());
        BTN_nextDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deck.drawedCards == 52) {
                    if (p1.getScore() > p2.getScore()) {
                        LBL_winner.setText("Player 1 Wins!");
                    } else if (p1.getScore() < p2.getScore()) {
                        LBL_winner.setText("Player 2 Wins!");
                    } else {
                        LBL_winner.setText("It's a draw!");
                    }
                    BTN_nextDraw.setEnabled(false);
                    return;
                }
                p1.currentCard = deck.drawCard();
                p2.currentCard = deck.drawCard();
                int id1 = getResources().getIdentifier(String.format("card_%s_%c", p1.currentCard.getValue(), p1.currentCard.getSign()), "drawable", getPackageName());
                int id2 = getResources().getIdentifier(String.format("card_%s_%c", p2.currentCard.getValue() , p2.currentCard.getSign()), "drawable", getPackageName());
                Log.d("pttt2", "" + id1);
                Log.d("pttt2", "" + id2);
                IMG_leftCard.setImageResource(id1);
                IMG_rightCard.setImageResource(id2);
                calculatePoint(p1.currentCard, p2.currentCard);
            }
        });
    }

    private void calculatePoint(Card card1, Card card2) {
        if (card1.getValue() > card2.getValue()) {
            p1.addPoint();
            LBL_leftScore.setText("" + p1.getScore());
        }
        else if (card1.getValue() < card2.getValue()) {
            p2.addPoint();
            LBL_rightScore.setText("" + p2.getScore());
        } else {
            p1.addPoint();
            p2.addPoint();
            LBL_leftScore.setText("" + p1.getScore());
            LBL_rightScore.setText("" + p2.getScore());
        }

    }

    private void findViews() {
        BTN_nextDraw = findViewById(R.id.BTN_nextDraw);
        LBL_leftScore = findViewById(R.id.LBL_leftScore);
        LBL_rightScore = findViewById(R.id.LBL_rightScore);
        LBL_winner = findViewById(R.id.LBL_winner);
        IMG_leftCard = findViewById(R.id.IMG_leftCard);
        IMG_rightCard = findViewById(R.id.IMG_rightCard);
    }


    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();
        Log.d("pttt", "Timer Stopped");
        //Stop Timer
    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }
}