package com.stropa.clients;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CovidAPIRequestURL implements RequestURL {

    private String endpoint;
    private Map<String, String> queryParams;

    public CovidAPIRequestURL(String url, Map<String, String> queryParams){
        this.endpoint = url;
        this.queryParams = queryParams;
    }

    public CovidAPIRequestURL(){
    }

    public String getRequestURL(){
        StringBuilder result = new StringBuilder(endpoint);
        if(!queryParams.isEmpty()){
            Iterator<Map.Entry<String, String>> iterator =  queryParams.entrySet().iterator();
            Map.Entry<String, String> param = iterator.next();
            result.append("?" + param.getKey() + "=" + param.getValue());
            while(iterator.hasNext()){
                param = iterator.next();
                result.append("&" + param.getKey() + "=" + param.getValue());
            }
        }

        return result.toString();
    }


    public static final class RequestBuilder {
        private final String covidAPIEndpoint = "http://api.covid19api.com";
        private String area = "";
        private String timeInterval = "";
        private String dataType = "";
        private Map<String, String> params = new HashMap<>();


        private RequestBuilder() {
        }

        public static RequestBuilder aRequest() {
            return new RequestBuilder();
        }


        public RequestBuilder fromDayOne(){
            timeInterval = "dayOne";
            return this;
        }

        public RequestBuilder fromLastMonth(){
            params.put("from", LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            params.put("to", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            return this;
        }

        public RequestBuilder fromLastSixMonths(){
            params.put("from", LocalDateTime.now().minusMonths(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            params.put("to", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            return this;
        }

        public RequestBuilder confirmed(){
            dataType = "status/confirmed";
            return this;
        }

        public RequestBuilder recovered(){
            dataType = "status/recovered";
            return this;
        }

        public RequestBuilder deaths(){
            dataType = "status/deaths";
            return this;
        }

        public RequestBuilder country(String country){
            area = "country/" + country;
            return this;
        }

        public RequestBuilder summary(){
            area = "summary";
            return this;
        }

        public CovidAPIRequestURL build() {
            CovidAPIRequestURL covidAPIRequest = new CovidAPIRequestURL();
            if(area.equals("summary")) covidAPIRequest.endpoint = covidAPIEndpoint.concat("/" + area);
            else covidAPIRequest.endpoint = covidAPIEndpoint + (timeInterval.isEmpty() ? "" : "/" + timeInterval) + "/" +
                    area + "/" + dataType;
            covidAPIRequest.queryParams = params;
            return covidAPIRequest;
        }
    }
}
