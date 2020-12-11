package com.example.firstassignment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment_Map extends Fragment {


    private TextView testing;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        findViews(view);
        initViews();

        return view;
    }

    public void showLocationOnMap(double lat, double lon) {
        testing.setText(lat + "\n" +  lon);
    }

    private void initViews() {
        testing.setText("Testing");
    }

    private void findViews(View view) {
        testing = view.findViewById(R.id.testing);
    }
}
