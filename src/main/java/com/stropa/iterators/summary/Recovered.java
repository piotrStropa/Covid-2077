package com.stropa.iterators.summary;

import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class Recovered implements SummaryEntryStrategy {
    @Override
    public XYChart.Data<String, Number> get(JSONObject summaryEntry) {
        return new XYChart.Data<>(summaryEntry.getString("Country"), summaryEntry.getInt("TotalRecovered"));
    }
}