package com.example.firstassignment;

import android.content.Context;
import android.content.SharedPreferences;

public class MySP {
    private static MySP instance;
    private final SharedPreferences prefs;
    public final String SP_FILE = "MY_SP";

    private MySP(Context context) {
        prefs = context.getSharedPreferences(SP_FILE, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new MySP(context.getApplicationContext());
        }
    }

    public static MySP getInstance() {
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putDouble(String key, double value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, (long)value);
        editor.apply();
    }

    public double getDouble(String key, double def) {
        return ((double)prefs.getLong(key, (long)def));
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public int getInt(String key, int def) {
        return prefs.getInt(key,def);
    }

    public void removeKey(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void clearCache() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
