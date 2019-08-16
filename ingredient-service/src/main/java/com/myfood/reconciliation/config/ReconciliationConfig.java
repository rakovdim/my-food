package com.myfood.reconciliation.config;

import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.service.clients.ClientIntegration;
import com.myfood.reconciliation.service.clients.inmemory.CategoriesInMemoryClient;
import com.myfood.reconciliation.service.clients.inmemory.IngredientsInMemoryClient;
import com.myfood.reconciliation.service.datasources.DataSourceType;
import com.myfood.reconciliation.service.datasources.ReconciliationDataSource;
import com.myfood.reconciliation.service.datasources.google.GoogleSheetsReconciliationDataSource;
import com.myfood.reconciliation.service.processors.DataProcessor;
import com.myfood.reconciliation.service.processors.impl.CategoryDataProcessor;
import com.myfood.reconciliation.service.processors.impl.DishDataProcessor;
import com.myfood.reconciliation.service.processors.impl.IngredientDataProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rakov on 12.08.2019.
 */
@Configuration
public class ReconciliationConfig {

    @Bean(name = "dataSources")
    public Map<DataSourceType, ReconciliationDataSource> getDataSources(GoogleSheetsReconciliationDataSource googleDataSource) {
        return new HashMap<DataSourceType, ReconciliationDataSource>() {
            {
                put(DataSourceType.google, googleDataSource);
            }
        };
    }

    @Bean(name = "clientIntegrations")
    public HashMap<EntityType, ClientIntegration> getClientIntegrations(IngredientsInMemoryClient ingredientsInMemoryClient, CategoriesInMemoryClient categoriesInMemoryClient) {
        return new HashMap<EntityType, ClientIntegration>() {
            {
                put(EntityType.ingredients, ingredientsInMemoryClient);
                put(EntityType.categories, categoriesInMemoryClient);
            }
        };
    }

    @Bean(name = "dataProcessors")
    public HashMap<EntityType, DataProcessor> getDataProcessors(IngredientDataProcessor ingredientDataProcessor,
                                                                DishDataProcessor dishDataProcessor,
                                                                CategoryDataProcessor categoryDataProcessor) {
        return new HashMap<EntityType, DataProcessor>() {
            {
                put(EntityType.ingredients, ingredientDataProcessor);
                put(EntityType.categories, categoryDataProcessor);
                put(EntityType.dishes, dishDataProcessor);
            }
        };
    }
}
