package com.example.currencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currencyapp.adapter.CurrencyAdapter;
import com.example.currencyapp.api.ApiFactory;
import com.example.currencyapp.api.ApiService;
import com.example.currencyapp.pojo.Currency;
import com.example.currencyapp.pojo.CurrencyResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCurrency;
    private CurrencyAdapter adapter;
    private TextView textViewCurrencyName;
    private TextView textViewCurrencyCharCode;
    private TextView textViewCurrencyValue;
//    private Switch switch1;
    private Disposable disposable;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewCurrencyName = findViewById(R.id.textViewCurrencyName);
        textViewCurrencyCharCode = findViewById(R.id.textViewCurrencyCharCode);
        textViewCurrencyValue = findViewById(R.id.textViewCurrencyValue);
        recyclerViewCurrency = findViewById(R.id.recyclerViewCurrency);
//        switch1 = findViewById(R.id.switch1);
        adapter = new CurrencyAdapter();
        adapter.setCurrencies(new ArrayList<Currency>());
        recyclerViewCurrency.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCurrency.setAdapter(adapter);
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiServce();
        disposable = apiService.getCurrency()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CurrencyResponse>() {
                    @Override
                    public void accept(CurrencyResponse currencyResponse) throws Exception {
                        adapter.setCurrencies(currencyResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int position_viewHolder = viewHolder.getAdapterPosition();
                int position_target = target.getAdapterPosition();
                Collections.swap(adapter.currencies, position_viewHolder, position_target);
                adapter.notifyItemMoved(position_viewHolder, position_target);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                adapter.currencies.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        helper.attachToRecyclerView(recyclerViewCurrency);
    }

    @Override
    protected void onDestroy() {
        if(disposable != null){
            disposable.dispose();
        }
        super.onDestroy();
    }

    public void OnClickManageCurrencies(MenuItem item) {
//        switch1.setVisibility(View.VISIBLE);
    }
}


