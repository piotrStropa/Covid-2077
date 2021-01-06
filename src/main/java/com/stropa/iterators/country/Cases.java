package com.stropa.iterators.country;

import com.stropa.iterators.summary.SummaryEntryStrategy;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class Cases implements CountryEntryStrategy {
    @Override
    public XYChart.Data<String, Number> get(JSONObject countryEntry) {
        return new XYChart.Data<>(countryEntry.getString("Date"), countryEntry.getInt("Cases"));
    }
}
