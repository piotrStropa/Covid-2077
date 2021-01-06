package com.stropa.data;

import com.stropa.iterators.Iterator;
import com.stropa.iterators.country.Cases;
import com.stropa.iterators.country.CountryJSONIterator;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class CoutryJSONObject extends JSONObject{
    private JSONObject countryResponse;

    public CoutryJSONObject(byte[] response){
        countryResponse = new JSONObject(response);
    }

    public Iterator<XYChart.Data<String, Number>> casesIterator(){
        return new CountryJSONIterator(countryResponse, new Cases());
    }
}
