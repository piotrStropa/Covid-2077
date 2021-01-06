package com.stropa.data;

import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public abstract class GraphData {
    public JSONObject responseData;
    String xAxisName;
    String yAxisName;

    public abstract XYChart.Series getSeries();
}
