package com.example.exchange_rates.activities;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.FragmentManager;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.exchange_rates.R;
        import com.example.exchange_rates.adapters.ExchangeRatesAdapter;
        import com.example.exchange_rates.connections.ConnectApiNBP;
        import com.example.exchange_rates.connections.Internet;
        import com.example.exchange_rates.data.Currency;
        import com.example.exchange_rates.data.CurrencyExtraData;
        import com.example.exchange_rates.data.EffectiveDates;
        import com.example.exchange_rates.files.JSONUtils;
        import com.example.exchange_rates.fragments.CurrencyInfoDialog;
        import com.example.exchange_rates.shared_preferences.SharedPreferencesUtils;

        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private TextView text;
    private TextView helper;
    private TextView date;

    private ListView exchange_info;

    private List<Currency> currencies;
    private List<CurrencyExtraData> currencyExtraData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        loadDates();
        updateData();
        getDataFromMemory();
        setAdapterToListView();
        setListenerToListView();
    }

    private void initLayout() {
        info = findViewById(R.id.info);
        text = findViewById(R.id.text);
        helper = findViewById(R.id.helper);
        date = findViewById(R.id.date);
        exchange_info = findViewById(R.id.exchange_info);
    }

    private void loadDates() {
        SharedPreferencesUtils.loadCityInfoFromSharedPreferences(this.getApplicationContext());
        date.setText(EffectiveDates.aTableDate);
        getCurrentDate();
    }

    private void getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date currentDate = new Date();
        EffectiveDates.currentDate = formatter.format(currentDate);
    }

    private void getDataFromMemory() {
        currencies = JSONUtils.getCurrencyInfoFromJSONArray(this, "a");
        currencyExtraData = JSONUtils.getCurrencyExtraInfoFromJSONArray(this, "c");
    }

    private void updateData() {
        if(Internet.isInternetAvailable(this.getApplicationContext())) {
            if(!EffectiveDates.aTableDate.equals(EffectiveDates.currentDate)) {
                ConnectApiNBP.getExchangeRates("a", this.getApplicationContext());
                loadDates();
            }
            if(!EffectiveDates.cTableDate.equals(EffectiveDates.currentDate)) {
                ConnectApiNBP.getExchangeRates("c", this.getApplicationContext());
                loadDates();
            }
        } else {
            Toast.makeText(this.getApplicationContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapterToListView() {
        Currency[] input = new Currency[currencies.size()];
        currencies.toArray(input);
        ExchangeRatesAdapter adapter = new ExchangeRatesAdapter(
                this.getApplication(),
                input
        );
        exchange_info.setAdapter(adapter);
    }

    private void setListenerToListView() {
        exchange_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCurrencyDialog(position);
            }
        });
    }

    private void showCurrencyDialog(int position) {
        FragmentManager fm = getSupportFragmentManager();
        CurrencyInfoDialog currencyInfoDialog = CurrencyInfoDialog.newInstance(findInfoAboutCurrency(currencies.get(position)));
        currencyInfoDialog.show(fm, "fragment_currency_info");
    }

    private CurrencyExtraData findInfoAboutCurrency(Currency currency) {
        for (CurrencyExtraData val : currencyExtraData) {
            if(val.getCode().equals(currency.getCode()))
                return val;
        }

        return new CurrencyExtraData("No Data","No Data", 0, 0);
    }

}