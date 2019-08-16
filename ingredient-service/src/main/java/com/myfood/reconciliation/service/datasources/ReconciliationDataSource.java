package com.myfood.reconciliation.service.datasources;

/**
 * Created by rakov on 08.08.2019.
 */
public interface ReconciliationDataSource {
    public LoadedDataResponse loadData(LoadingRequest loadingRequest);
}
