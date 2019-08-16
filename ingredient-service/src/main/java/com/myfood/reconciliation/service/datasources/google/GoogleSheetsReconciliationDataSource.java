package com.myfood.reconciliation.service.datasources.google;

import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.model.dto.PlainEntity;
import com.myfood.reconciliation.service.datasources.DataSourceType;
import com.myfood.reconciliation.service.datasources.LoadedDataResponse;
import com.myfood.reconciliation.service.datasources.LoadingRequest;
import com.myfood.reconciliation.service.datasources.ReconciliationDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

@Component
@ConditionalOnProperty(name = "com.myfood.reconciliation.google_enabled", havingValue = "true")
public class GoogleSheetsReconciliationDataSource implements ReconciliationDataSource {

    private GoogleDocsIntegration googleDocsIntegration;

    @Autowired
    public GoogleSheetsReconciliationDataSource(GoogleDocsIntegration googleDocsIntegration) {
        this.googleDocsIntegration = googleDocsIntegration;
    }

    @Override
    public LoadedDataResponse loadData(LoadingRequest loadingRequest) {

        LoadedDataResponse report = new LoadedDataResponse(DataSourceType.google);
        List<EntityType> entityTypes = loadingRequest.getEntityTypes();
        List<List<List<Object>>> allSheetsValues = googleDocsIntegration.getSheetsData(loadingRequest);
        Iterator keysIterator;
        Iterator oneRowIterator;
        int i = 0;
        for (List<List<Object>> sheetObjects : allSheetsValues) {
            if (CollectionUtils.isEmpty(sheetObjects))
                continue;
            Iterator<List<Object>> sheetObjectsIterator = sheetObjects.iterator();

            List keys = sheetObjectsIterator.next();

            EntityType entityType = entityTypes.get(i++);
            while (sheetObjectsIterator.hasNext()) {
                PlainEntity plainEntity = new PlainEntity(entityType);

                keysIterator = keys.iterator();
                oneRowIterator = sheetObjectsIterator.next().iterator();

                while (oneRowIterator.hasNext()) {
                    String key = keysIterator.next().toString();
                    String value = oneRowIterator.next().toString();

                    plainEntity.setValue(key.toLowerCase(), value);
                }
                report.addPlainEntity(plainEntity);
            }
        }
        return report;


    }


}
