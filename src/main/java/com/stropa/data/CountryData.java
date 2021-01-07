package com.stropa.data;

import com.stropa.iterators.Iterator;
import com.stropa.iterators.country.Cases;
import com.stropa.iterators.country.CountryJSONIterator;
import javafx.scene.chart.XYChart;
import org.json.JSONArray;
import org.json.JSONObject;

public class CountryData {
    private JSONArray countryResponse;

    public CountryData(byte[] response){
        countryResponse = new JSONArray(new String(response));
    }

    public Iterator<XYChart.Data<String, Number>> casesIterator(){
        return new CountryJSONIterator(countryResponse, new Cases());
    }
}
