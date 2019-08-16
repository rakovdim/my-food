package com.myfood.reconciliation.controller;

import com.myfood.reconciliation.consts.ReconcConsts;
import com.myfood.reconciliation.model.EntityType;
import com.myfood.reconciliation.service.ReconciliationService;
import com.myfood.reconciliation.service.datasources.DataSourceType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ReconcConsts.RECONC_SERVICE_V1_URL)
public class ReconciliationController {

    private ReconciliationService reconciliationService;

    public ReconciliationController(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    @GetMapping("/{dataSourceType}/reconcile/{entityType}")
    public List<UUID> reconcileEntity(@PathVariable DataSourceType dataSourceType, @PathVariable EntityType entityType) {
        return reconciliationService.reconcileEntities(dataSourceType, entityType);
    }
}
