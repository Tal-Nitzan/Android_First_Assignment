package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.firstassignment.Constants.SP_FILE;

public class LoginActivity extends AppCompatActivity {

    ImageButton login_BTN_newGame;
    ImageButton login_BTN_records;
    TextView login_LBL_newGame;
    TextView login_LBL_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
//        clearCache(); // To clear cache
        findViews();
        initViews();
    }


    private void initViews() {
        login_BTN_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        login_BTN_records.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openRecordsActivity();
            }
        });
    }


    private void clearCache() {
        SharedPreferences preferences = getSharedPreferences(SP_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    private void findViews() {
        login_BTN_newGame = findViewById(R.id.login_BTN_newGame);
        login_BTN_records = findViewById(R.id.login_BTN_records);
        login_LBL_newGame = findViewById(R.id.login_LBL_newGame);
        login_LBL_records = findViewById(R.id.login_LBL_records);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openRecordsActivity() {
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivity(intent);
        finish();
    }
}