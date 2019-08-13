package com.myfood.reconciliation.service.datasources.google;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by rakov on 12.08.2019.
 */
@Component
@PropertySource("classpath:google_reconciliation.properties")
@ConditionalOnProperty(name = "com.myfood.reconciliation.google_enabled", havingValue = "true")
public class GoogleReconciliationProperties {
    @Value("${com.myfood.reconciliation.google.document_id}")
    @Getter
    private String documentId;
    @Value("${com.myfood.reconciliation.google.sa_config_file}")
    @Getter
    private String saConfigFile;

}
