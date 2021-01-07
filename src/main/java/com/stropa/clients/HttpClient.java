package com.stropa.clients;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClient {
    private int connectionTimeout;

    private static HttpClient instance;
    private java.net.http.HttpClient client;

    private HttpClient(int connectionTimeout){
        this.connectionTimeout = connectionTimeout;
        client = java.net.http.HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMillis(connectionTimeout))
                    .version(java.net.http.HttpClient.Version.HTTP_2)
                    .followRedirects(java.net.http.HttpClient.Redirect.ALWAYS)
                    .build();
    }

    public static HttpClient getInstance() {
        if(instance == null) instance = new HttpClient(100000);
        return instance;
    }

    public byte[] execute(Request request) {
        try {
            URL url = new URL(request.getRequestURL());
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                        .uri(url.toURI())
                        .timeout(Duration.ofMillis(connectionTimeout));

            switch(request.getRequestMethod()){
                case GET : builder = builder.GET(); break;
                case POST : builder = builder.POST(HttpRequest.BodyPublishers.ofByteArray(request.getRequestBody())); break;
                case PUT : builder = builder.PUT(HttpRequest.BodyPublishers.ofByteArray(request.getRequestBody())); break;
                case DELETE : builder = builder.DELETE(); break;
            }

            HttpRequest httpRequest = builder.build();
            return client.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray()).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}
