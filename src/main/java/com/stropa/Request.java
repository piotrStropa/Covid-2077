package com.stropa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Request{

    private String endpoint;
    private Map<String, String> queryParams;

    public Request(String url, Map<String, String> queryParams){
        this.endpoint = url;
        this.queryParams = queryParams;
    }

    public Request(){
    }

    public String getUrlWithParams(){
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
        private final String endpoint = "https://api.covid19api.com";
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

        public Request build() {
            Request request = new Request();
            if(area.equals("summary")) request.endpoint = endpoint.concat("/" + area);
            else request.endpoint = endpoint + (timeInterval.isEmpty() ? "" : "/" + timeInterval) + "/" +
                    area + "/" + dataType;
            request.queryParams = params;
            return request;
        }
    }
}
