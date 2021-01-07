package com.stropa.iterators.summary;

import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class Mortality implements SummaryEntryStrategy {
    @Override
    public XYChart.Data<String, Number> get(JSONObject summaryEntry) {
        String country = summaryEntry.getString("Country");
        if(country.length() > 15) country = country.substring(0, 15).concat("...");
        if(summaryEntry.getInt("TotalConfirmed") == 0) return new XYChart.Data<>(country, 0);
        return new XYChart.Data<>(country,
                1.0*summaryEntry.getInt("TotalDeaths")/ summaryEntry.getInt("TotalConfirmed"));
    }
}
