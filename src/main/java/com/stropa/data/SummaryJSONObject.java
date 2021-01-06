package com.stropa.data;

import com.stropa.iterators.Iterator;
import com.stropa.iterators.summary.SummaryEntryStrategy;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class SummaryJSONObject{
    private JSONObject summaryEntries;

    public SummaryJSONObject(byte[] response){
        summaryEntries = new JSONObject(response);
    }


    public Iterator<XYChart.Data<String, Number>> iterator(){
        return null;
    }
}
