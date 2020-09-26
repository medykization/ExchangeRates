package com.example.exchange_rates.connections;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.exchange_rates.files.JSONUtils;
import com.example.exchange_rates.shared_preferences.SharedPreferencesUtils;

import org.json.JSONArray;
import org.json.JSONException;

public class ConnectApiNBP {

    public static void getExchangeRates(final String type, final Context context) {
        String url = "http://api.nbp.pl/api/exchangerates/tables/" + type + "/today/?format=json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            SharedPreferencesUtils.updateSharedPreferences(response.getJSONObject(0).getString("effectiveDate"), type, context);
                            JSONUtils.saveCurrencyInfo(response.getJSONObject(0).getJSONArray("rates"), type, context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Incorrect city name", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonArrayRequest);
    }
}
