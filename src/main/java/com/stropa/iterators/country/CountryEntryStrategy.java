package com.stropa.iterators.country;

import javafx.scene.chart.XYChart;
import org.json.JSONObject;

@FunctionalInterface
public interface CountryEntryStrategy {
    XYChart.Data<String, Number> get(JSONObject countryEntry);
}
