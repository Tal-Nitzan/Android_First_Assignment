package com.example.firstassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private View view;
    private GoogleMap map;
    MapView mapFragment_MAP_theMap;

    public Fragment_Map() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        findViews(view);
        initViews();
        return view;
    }

    public void showLocationOnMap(String name, int score, double lat, double lon) {
        LatLng location = new LatLng(lat, lon);
        map.addMarker(new MarkerOptions().position(location).title(name + " - " + score));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 9.0f));
    }

    private void initViews() {
        if (mapFragment_MAP_theMap != null) {
            mapFragment_MAP_theMap.onCreate(null);
            mapFragment_MAP_theMap.onResume();
            mapFragment_MAP_theMap.getMapAsync(this);
        }
    }

    private void findViews(View view) {
        mapFragment_MAP_theMap = view.findViewById(R.id.mapFragment_MAP_theMap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
    }
}
