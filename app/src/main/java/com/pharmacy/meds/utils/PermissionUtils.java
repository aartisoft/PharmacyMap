package com.pharmacy.meds.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PermissionUtils {

    private final static String PREFS_NAME = "Permission.Shared";
    private static SharedPreferences permissionSharedPreference;

    public static void initSharedPreferences(Context context) {
        permissionSharedPreference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void clearData() {
        if (null != permissionSharedPreference) {
            SharedPreferences.Editor editor = permissionSharedPreference.edit();
            editor.clear();
            editor.apply();
        }
    }

    public static void removeData(String key) {
        if (null != permissionSharedPreference) {
            SharedPreferences.Editor editor = permissionSharedPreference.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    public static int getInt(String key, int def) {
        if (key == null || key.equals("")) {
            return def;
        }
        return permissionSharedPreference.getInt(key, def);
    }

    public static boolean getBoolean(String key, boolean def) {
        if (key == null || key.equals("")) {
            return def;
        }
        return permissionSharedPreference.getBoolean(key, def);
    }

    public static String getString(String key, String def) {
        if (key == null || key.equals("")) {
            return def;
        }
        return permissionSharedPreference.getString(key, def);
    }

    public static void setInt(String key, int value) {
        SharedPreferences.Editor editor = permissionSharedPreference.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = permissionSharedPreference.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = permissionSharedPreference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
