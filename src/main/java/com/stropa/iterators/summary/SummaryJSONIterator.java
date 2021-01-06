package com.stropa.iterators.summary;

import com.stropa.iterators.Iterator;
import javafx.scene.chart.XYChart;
import org.json.JSONArray;
import org.json.JSONObject;

public class SummaryJSONIterator implements Iterator<XYChart.Data<String, Number>> {
    JSONArray countryEntries;
    SummaryEntryStrategy collectionStrategy;
    int size;
    int currentIndex;

    public SummaryJSONIterator(JSONObject summaryResponse, SummaryEntryStrategy collectionStrategy){
        countryEntries = summaryResponse.getJSONArray("Countries");
        this.collectionStrategy = collectionStrategy;
        size = countryEntries.length();
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < size;
    }

    @Override
    public XYChart.Data<String, Number> next() {
        JSONObject countryEntry = countryEntries.getJSONObject(currentIndex);
        currentIndex++;
        return collectionStrategy.get(countryEntry);
    }
}
