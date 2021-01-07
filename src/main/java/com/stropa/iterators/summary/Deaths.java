package com.stropa.iterators.summary;

import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class Deaths implements SummaryEntryStrategy {
    @Override
    public XYChart.Data<String, Number> get(JSONObject summaryEntry) {
        String country = summaryEntry.getString("Country");
        if(country.length() > 15) country = country.substring(0, 15).concat("...");
        return new XYChart.Data<>(country, summaryEntry.getInt("TotalDeaths"));
    }
}
