package com.stropa;
import com.stropa.data.DataType;
import com.stropa.data.ResponseIteratorFactory;
import com.stropa.data.TimeInterval;
import com.stropa.iterators.Iterator;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GraphDataLoader {
    private static GraphDataLoader instance;
    private XYChart.Series<String, Number> chartData;

    public static GraphDataLoader getInstance() {
        if(instance == null) {
            instance = new GraphDataLoader();
        }
        return instance;
    }

    public XYChart.Series<String, Number> getChartData() {
        return chartData;
    }

    public DataType toDataType(String text){
        if(text.equals("Total Confirmed") || text.equals("Confirmed")) return DataType.CONFIRMED;
        if(text.equals("Total Deaths") || text.equals("Deaths")) return DataType.DEATHS;
        if(text.equals("Total Recovered") || text.equals("Recovered")) return DataType.RECOVERED;
        else return DataType.MORTALITY;
    }

    public TimeInterval toTimeInterval(String text){
        if(text.equals("Last month")) return TimeInterval.LAST_MONTH;
        if(text.equals("Last six months")) return TimeInterval.LAST_SIX_MONTHS;
        return TimeInterval.DAY_ONE;
    }

    public void loadSummaryData(DataType type){
        Iterator<XYChart.Data<String, Number>> iterator = ResponseIteratorFactory.getInstance()
                .createDataIterator(true, null, type, null);

        List<XYChart.Data<String, Number>> chartData = new ArrayList<>();
        while(iterator.hasNext()){
            chartData.add(iterator.next());
        }
        this.chartData = new XYChart.Series<>();
        this.chartData.getData().addAll(chartData.stream()
                .sorted(Comparator.comparingDouble(x -> -x.getYValue().doubleValue()))
                .limit(20)
                .collect(Collectors.toList()));
    }

    public void loadDailyData(String country, DataType type, TimeInterval interval){
        Iterator<XYChart.Data<String, Number>> iterator = ResponseIteratorFactory.getInstance()
                .createDataIterator(false, interval, type, country);

        chartData = new XYChart.Series<>();
        while(iterator.hasNext()){
            chartData.getData().add(iterator.next());
        }
    }
}
