package com.artem.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    static Prefs instance;
    private final SharedPreferences prefs;

    private Prefs(Context context) {
        prefs = context.getSharedPreferences(context.getPackageName() + ".data.preference", 0);
    }

    public static Prefs getInstance(Context context) {
        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    public void setString(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return prefs.getString(key, null);
    }
}
