package com.example.firstassignment;

import android.app.Application;

public class App extends Application {

    public App() {}

    @Override
    public void onCreate() {
        super.onCreate();

        MySP.init(this);

    }
}
