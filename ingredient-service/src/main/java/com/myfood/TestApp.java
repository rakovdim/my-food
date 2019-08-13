package com.myfood;

import com.myfood.reconciliation.model.dto.IngredientDTO;
import com.myfood.reconciliation.service.ReconciliationService;
import com.myfood.reconciliation.service.clients.inmemory.IngredientsInMemoryClient;
import com.myfood.reconciliation.service.datasources.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by rakov on 12.08.2019.
 */
@SpringBootApplication
public class TestApp implements CommandLineRunner {
    @Autowired
    private ReconciliationService reconciliationService;
    @Autowired
    private IngredientsInMemoryClient client;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApp.class);
    }


    @Override
    public void run(String... args) throws Exception {

        List<UUID> ids = reconciliationService.reconcileCategories(DataSourceType.google);

        System.out.println("aaaaa: " + ids);
    }
}
