package com.stropa.data;

import com.stropa.iterators.Iterator;
import com.stropa.iterators.summary.*;
import javafx.scene.chart.XYChart;
import org.json.JSONObject;

public class SummaryData {
    private JSONObject summaryEntries;

    public SummaryData(byte[] response){
        summaryEntries = new JSONObject(new String(response));
    }

    public Iterator<XYChart.Data<String, Number>> confirmedIterator(){
        return new SummaryJSONIterator(summaryEntries, new Confirmed());
    }

    public Iterator<XYChart.Data<String, Number>> deathsIterator(){
        return new SummaryJSONIterator(summaryEntries, new Deaths());
    }

    public Iterator<XYChart.Data<String, Number>> recoveredIterator(){
        return new SummaryJSONIterator(summaryEntries, new Recovered());
    }

    public Iterator<XYChart.Data<String, Number>> mortalityIterator(){
        return new SummaryJSONIterator(summaryEntries, new Mortality());
    }
}
