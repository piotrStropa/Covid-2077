package com.stropa.iterators.country;

import com.stropa.iterators.Iterator;
import javafx.scene.chart.XYChart;
import org.json.JSONArray;
import org.json.JSONObject;

public class CountryJSONIterator implements Iterator<XYChart.Data<String, Number>> {
    JSONArray dailyEntries;
    CountryEntryStrategy collectionStrategy;
    int size;
    int currentIndex;

    public CountryJSONIterator(JSONArray countryResponse, CountryEntryStrategy collectionStrategy){
        dailyEntries = countryResponse;
        this.collectionStrategy = collectionStrategy;
        size = dailyEntries.length();
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < size;
    }

    @Override
    public XYChart.Data<String, Number> next() {
        JSONObject countryEntry = dailyEntries.getJSONObject(currentIndex);
        currentIndex++;
        return collectionStrategy.get(countryEntry);
    }
}
