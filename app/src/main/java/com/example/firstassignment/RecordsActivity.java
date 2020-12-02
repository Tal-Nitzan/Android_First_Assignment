package com.example.firstassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class RecordsActivity extends AppCompatActivity {

    private TextView records_LBL_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_records);
        findViews();
        initViews();
    }


    private void initViews() {
        records_LBL_temp.setText("This is Records Activty");
    }

    private void findViews() {
        records_LBL_temp = findViewById(R.id.records_LBL_temp);
    }
}