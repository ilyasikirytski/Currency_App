package com.example.currencyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyapp.R;
import com.example.currencyapp.pojo.Currency;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    public List<Currency> currencies;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        holder.textViewCurrencyName.setText(currency.getName());
        holder.textViewCurrencyCharCode.setText(currency.getCharCode());
        holder.textViewCurrencyValue.setText(currency.getRate());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCurrencyName;
        private TextView textViewCurrencyCharCode;
        private TextView textViewCurrencyValue;
//        private Switch switch1;
        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCurrencyName = itemView.findViewById(R.id.textViewCurrencyName);
            textViewCurrencyCharCode = itemView.findViewById(R.id.textViewCurrencyCharCode);
            textViewCurrencyValue = itemView.findViewById(R.id.textViewCurrencyValue);
//            switch1 = itemView.findViewById(R.id.switch1);
        }
    }
}
