package com.myfood.reconciliation.service;

import com.myfood.ingredients.dto.IngredientDTO;
import com.myfood.reconciliation.InternalReconciliationException;
import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.service.clients.ClientIntegration;
import com.myfood.reconciliation.service.datasources.DataSourceType;
import com.myfood.reconciliation.service.datasources.LoadedDataResponse;
import com.myfood.reconciliation.service.datasources.LoadingRequest;
import com.myfood.reconciliation.service.datasources.ReconciliationDataSource;
import com.myfood.reconciliation.service.processors.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by rakov on 08.08.2019.
 */
@Service
public class ReconciliationService {
    private final Map<DataSourceType, ReconciliationDataSource> dataSources;
    private Map<EntityType, DataProcessor> dataProcessors;
    private Map<EntityType, ClientIntegration> clientIntegrations;

    @Autowired
    public ReconciliationService(@Qualifier("dataSources") Map<DataSourceType, ReconciliationDataSource> dataSources,
                                 @Qualifier("dataProcessors") Map<EntityType, DataProcessor> dataProcessors,
                                 @Qualifier("clientIntegrations") Map<EntityType, ClientIntegration> clientIntegrations) {
        this.dataSources = dataSources;
        this.dataProcessors = dataProcessors;
        this.clientIntegrations = clientIntegrations;
    }

    public List<UUID> reconcileIngredients(DataSourceType dataSourceType) {
        return reconcileEntities(dataSourceType, EntityType.ingredients);
    }


    public List<UUID> reconcileCategories(DataSourceType dataSourceType) {
        return reconcileEntities(dataSourceType, EntityType.categories);
    }

    public List<UUID> reconcileDishes(DataSourceType dataSourceType) {
        return reconcileEntities(dataSourceType, EntityType.dishes);
    }

    @SuppressWarnings(value = "unchecked")
    public List<UUID> reconcileEntities(DataSourceType dataSourceType, EntityType entityType) {
        ReconciliationDataSource dataSource = dataSources.get(dataSourceType);

        if (dataSource == null)
            throw new InternalReconciliationException("This type of reconciliation: " + dataSourceType + " is not supported yet");

        LoadingRequest loadingRequest = new LoadingRequest(entityType);

        LoadedDataResponse response = dataSource.loadData(loadingRequest);

        if (response.isEmpty(entityType))
            return Collections.emptyList();

        DataProcessor<IngredientDTO> ingredientDataProcessor = dataProcessors.get(entityType);

        if (ingredientDataProcessor == null)
            throw new InternalReconciliationException("No Data Converter for: " + entityType);

        List<IngredientDTO> ingredients = response.getLoadedEntities(entityType).stream().
                map(ingredientDataProcessor::convert).collect(Collectors.toList());


        ClientIntegration<IngredientDTO> clientIntegration = clientIntegrations.get(entityType);

        if (clientIntegration == null)
            throw new InternalReconciliationException("No Client Integration for: " + entityType);

        return clientIntegration.uploadAll(ingredients);
    }
}
