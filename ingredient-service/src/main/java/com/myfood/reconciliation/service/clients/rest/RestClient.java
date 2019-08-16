package com.myfood.reconciliation.service.clients.rest;

import com.myfood.reconciliation.service.clients.ClientIntegration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

public class RestClient<T> implements ClientIntegration {

    private RestTemplate restTemplate;

    @Override
    public List<UUID> uploadAll(List entities) {
        return null;
    }

    @Override
    public List findByIds(List names) {
        return null;
    }

    @Override
    public List findByNames(List names) {
        return null;
    }
}
