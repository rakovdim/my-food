package com.myfood.reconciliation.service.datasources.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.service.datasources.LoadingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoogleDocsIntegration {
    private GoogleReconciliationProperties config;

    @Autowired
    public GoogleDocsIntegration(GoogleReconciliationProperties config) {
        this.config = config;
    }

    public List<List<List<Object>>> getSheetsData(LoadingRequest loadingRequest) {
        try {

            Sheets sheetsService = getSheetsService(config);
            Sheets.Spreadsheets.Values.BatchGet batchGet = sheetsService.spreadsheets().values().batchGet(config.getDocumentId());

            batchGet.setRanges(loadingRequest.getEntityTypes().stream().map(this::entityTypeToString).collect(Collectors.toList()));
            BatchGetValuesResponse response = batchGet.execute();
            List<ValueRange> valueRanges = response.getValueRanges();
            return new ArrayList<List<List<Object>>>() {
                {
                    for (ValueRange range : valueRanges) {
                        add(range.getValues());
                    }
                }
            };
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private Credential getCredential(GoogleReconciliationProperties config) throws IOException {
        InputStream is = GoogleSheetsReconciliationDataSource.class.getResourceAsStream(config.getSaConfigFile());

        return GoogleCredential.fromStream(is).createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
    }


    private Sheets getSheetsService(GoogleReconciliationProperties config) throws GeneralSecurityException, IOException {
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), getCredential(config)).setApplicationName(
                "Google Sheets Client").build();
    }

    private String entityTypeToString(EntityType type) {
        return type.toString().toLowerCase();
    }
}
