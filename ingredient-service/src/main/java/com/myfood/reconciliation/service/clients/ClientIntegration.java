package com.myfood.reconciliation.service.clients;

import java.util.List;
import java.util.UUID;

public interface ClientIntegration<T> {

    public List<UUID> uploadAll(List<T> entities);

    public List<T> findByNames(List<String> names);

    public List<T> findByIds(List<UUID> names);
}
