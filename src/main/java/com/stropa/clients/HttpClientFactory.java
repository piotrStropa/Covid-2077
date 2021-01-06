package com.stropa.clients;

import com.stropa.clients.Client;
import com.stropa.clients.ClientFactory;
import com.stropa.clients.HttpGetClient;

public class HttpClientFactory implements ClientFactory {

    @Override
    public Client createClient() {
        return new HttpGetClient(5000, 1);
    }
}
