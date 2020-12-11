package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecordsActivity extends AppCompatActivity {


    private Fragment_Map fragment_map;
    private Fragment_Top10 fragment_top10;
    private ImageButton login_BTN_newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_records);
        findViews();
        initViews();


        fragment_top10 = new Fragment_Top10();
        fragment_top10.setCallBack_top10(callBack_top);
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_top10, fragment_top10).commit();

        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_map, fragment_map).commit();
    }

    private CallBack_Top10 callBack_top = new CallBack_Top10() {
        @Override
        public void displayLocation(double lat, double lon) {
            fragment_map.showLocationOnMap(lat, lon);
        }
    };


    private void initViews() {
        login_BTN_newGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecordsActivity.this, MainActivity.class));
            }
        });
    }

    private void findViews() {
        login_BTN_newGame = findViewById(R.id.login_BTN_newGame);
    }
}