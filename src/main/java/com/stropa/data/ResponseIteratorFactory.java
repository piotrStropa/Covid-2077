package com.stropa.data;

import com.stropa.clients.CovidAPIRequest;
import com.stropa.clients.HttpClient;
import com.stropa.clients.Request;
import com.stropa.iterators.Iterator;
import javafx.scene.chart.XYChart;

public class ResponseIteratorFactory {
    private static ResponseIteratorFactory instance;

    public static ResponseIteratorFactory getInstance(){
        if(instance == null) instance = new ResponseIteratorFactory();
        return instance;
    }

    public Iterator<XYChart.Data<String, Number>> createDataIterator(boolean isSummary, TimeInterval interval, DataType type, String country) {
        CovidAPIRequest.RequestBuilder requestBuilder = CovidAPIRequest.RequestBuilder.aRequest();
        if (isSummary) {
            Request request = requestBuilder.summary().build();
            byte[] response = HttpClient.getInstance().execute(request);
            System.out.println(new String(response));
            SummaryData data = new SummaryData(response);
            switch (type) {
                case CONFIRMED:
                    return data.confirmedIterator();
                case RECOVERED:
                    return data.recoveredIterator();
                case DEATHS:
                    return data.deathsIterator();
                case MORTALITY:
                    return data.mortalityIterator();

            }
        }

        switch (interval) {
            case LAST_MONTH : requestBuilder.fromLastMonth(); break;
            case LAST_SIX_MONTHS :requestBuilder.fromLastSixMonths(); break;
            default : requestBuilder.fromDayOne(); break;
        }

        switch (type) {
            case RECOVERED : requestBuilder.recovered();break;
            case DEATHS : requestBuilder.deaths();break;
            default : requestBuilder.confirmed();break;
        }
        requestBuilder.country(country);

        Request request = requestBuilder.build();
        byte[] response = HttpClient.getInstance().execute(request);
        System.out.println(new String(response));

        CountryData data = new CountryData(response);
        return data.casesIterator();
    }
}
