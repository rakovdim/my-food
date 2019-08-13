package com.myfood;


import com.myfood.reconciliation.controller.ReconciliationController;
import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.service.datasources.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Configuration
public class Application implements CommandLineRunner {

    @Autowired
    private ReconciliationController controller;
    @Autowired
    private ApplicationData applicationData;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        controller.reconcileEntity(DataSourceType.google, EntityType.categories);

        applicationData.uploadImages();
    }


}
