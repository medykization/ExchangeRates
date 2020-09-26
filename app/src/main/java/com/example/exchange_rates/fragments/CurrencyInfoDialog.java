package com.example.exchange_rates.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.exchange_rates.R;
import com.example.exchange_rates.data.CurrencyExtraData;

public class CurrencyInfoDialog extends DialogFragment {

    private TextView buyPrice;
    private TextView sellPrice;
    private TextView info1;
    private TextView info2;
    private TextView code;
    private TextView currencyName;

    public CurrencyInfoDialog () {

    }

    public static CurrencyInfoDialog newInstance(CurrencyExtraData currency) {
        Bundle args = new Bundle();
        CurrencyInfoDialog fragment = new CurrencyInfoDialog();
        args.putString("code",currency.getCode());
        args.putString("currency",currency.getCurrency());
        args.putDouble("buy", currency.getBuyPrice());
        args.putDouble("sell", currency.getSellPrice());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currency_info_dialog, container);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
    }

    private void initLayout(@NonNull View view) {
        buyPrice = view.findViewById(R.id.buy_price);
        sellPrice = view.findViewById(R.id.sell_price);
        code = view.findViewById(R.id.code);
        currencyName = view.findViewById(R.id.currency_name);
        info1 = view.findViewById(R.id.info1);
        info2 = view.findViewById(R.id.info2);

        code.setText(getArguments().getString("code"));
        currencyName.setText(getArguments().getString("currency"));
        buyPrice.setText(String.valueOf(getArguments().getDouble("buy")));
        sellPrice.setText(String.valueOf(getArguments().getDouble("sell")));

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
