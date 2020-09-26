package com.example.exchange_rates.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.exchange_rates.data.EffectiveDates;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {

    public static void updateSharedPreferences(String date, String type , Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        editor.putString(type, date);
        editor.commit();
    }

    public static void loadCityInfoFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", MODE_PRIVATE);
        EffectiveDates.aTableDate = sharedPreferences.getString("a", "none");
        EffectiveDates.cTableDate = sharedPreferences.getString("c", "none");
    }

}
