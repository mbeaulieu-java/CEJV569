package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.LogMedicationOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

public class MedLogDataController extends DataController implements LogMedicationOperation {

    public MedLogDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //setters amd getters

    public DataTransaction getTransaction() {
        return super.transaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
    }
}
