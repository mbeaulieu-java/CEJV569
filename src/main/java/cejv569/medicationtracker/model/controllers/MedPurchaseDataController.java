package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.MedicationPurchaseOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

public class MedPurchaseDataController extends DataController implements MedicationPurchaseOperation {

    public MedPurchaseDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    // setters and getters

    public DataTransaction getTransaction() {
        return super.transaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
    }
}
