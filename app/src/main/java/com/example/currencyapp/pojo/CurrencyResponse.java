package com.example.currencyapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "DailyExRates", strict = false)

public class CurrencyResponse {

@ElementList(name = "item", inline = true)

    private List<Currency> response = null;

    public List<Currency> getResponse() {
        return response;
    }
}
