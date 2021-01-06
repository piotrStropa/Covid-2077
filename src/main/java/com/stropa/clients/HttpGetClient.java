package com.stropa.clients;

import com.stropa.clients.Client;
import com.stropa.clients.RequestURL;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpGetClient implements Client {
    private int connectionTimeout;
    private int priority;
    private HttpClient client;

    public HttpGetClient(int connectionTimeout, int priority){
        this.connectionTimeout = connectionTimeout;
        this.priority = priority;
        client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofMillis(connectionTimeout))
                    .priority(priority)
                    .version(HttpClient.Version.HTTP_2)
                    .build();
    }

    @Override
    public byte[] execute(RequestURL requestURL) {
        try {
            URL url = new URL(requestURL.getRequestURL());
            HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(url.toURI())
                        .timeout(Duration.ofMillis(connectionTimeout))
                        .build();

            return client.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray()).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getPriority() {
        return priority;
    }
}
