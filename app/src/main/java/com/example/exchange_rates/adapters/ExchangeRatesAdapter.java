package com.example.exchange_rates.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exchange_rates.R;

import com.example.exchange_rates.data.Currency;

public class ExchangeRatesAdapter extends ArrayAdapter<Currency> {

    private final Context context;
    private final Currency[] values;

    public ExchangeRatesAdapter(Context context, Currency[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView code = (TextView) rowView.findViewById(R.id.code);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView unit = (TextView) rowView.findViewById(R.id.unit);
        TextView price = (TextView) rowView.findViewById(R.id.price);

        code.setText(values[position].getCode());
        name.setText(values[position].getCurrencyName());
        unit.setText("1 " + values[position].getCode() + "  =");
        price.setText(String.valueOf(values[position].getMidValue()));

        return rowView;
    }

}
