package com.stropa.iterators.summary;

import javafx.scene.chart.XYChart;
import org.json.JSONObject;

@FunctionalInterface
public interface SummaryEntryStrategy {
    XYChart.Data<String, Number> get(JSONObject entry);
}
