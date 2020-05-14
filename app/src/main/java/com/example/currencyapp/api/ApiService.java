package com.example.currencyapp.api;

import com.example.currencyapp.pojo.CurrencyResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("XmlExRates.aspx?ondate/13.05.2020")
    Observable<CurrencyResponse> getCurrency();
}
