package com.example.exchange_rates.files;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.exchange_rates.data.Currency;
import com.example.exchange_rates.data.CurrencyExtraData;

public class JSONUtils {

    public static void saveCurrencyInfo (JSONArray object, String type, Context context) {
        try {
            FileOutputStream fOut = context.openFileOutput(type + ".json",Context.MODE_PRIVATE);
            fOut.write(object.toString().getBytes());
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Currency> getCurrencyInfoFromJSONArray (Context context, String type) {
        JSONArray array = getJSONCurrencyArray(context, type);
        List<Currency> currencyInfo = new ArrayList<>();

        for(int i = 0; i < array.length(); i++) {
            JSONObject tmp = null;
            try {
                tmp = array.getJSONObject(i);
                currencyInfo.add(new Currency(tmp.getString("code"),
                                            tmp.getString("currency"),
                                            tmp.getDouble("mid")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return currencyInfo;
    }

    public static List<CurrencyExtraData> getCurrencyExtraInfoFromJSONArray (Context context, String type) {
        JSONArray array = getJSONCurrencyArray(context, type);
        List<CurrencyExtraData> currencyExtraData = new ArrayList<>();

        for(int i = 0; i < array.length(); i++) {
            JSONObject tmp = null;
            try {
                tmp = array.getJSONObject(i);
                currencyExtraData.add(new CurrencyExtraData(
                        tmp.getString("code"),
                        tmp.getString("currency"),
                        tmp.getDouble("bid"),
                        tmp.getDouble("ask")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return currencyExtraData;
    }

    public static JSONArray getJSONCurrencyArray (Context context, String type) {
        JSONArray result = null;
        try {
            FileInputStream fin = context.openFileInput(type + ".json");
            int size = fin.available();
            byte[] buffer = new byte[size];
            fin.read(buffer);
            fin.close();
            //System.out.println(new String(buffer));
            result = new JSONArray(new String(buffer));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
