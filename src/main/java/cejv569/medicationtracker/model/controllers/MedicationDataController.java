package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

public class MedicationDataController extends DataController implements ConfigureMedicationOperation {
    public MedicationDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //getters and setters

    public DataTransaction getTransaction() {
        return super.transaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
    }
}
