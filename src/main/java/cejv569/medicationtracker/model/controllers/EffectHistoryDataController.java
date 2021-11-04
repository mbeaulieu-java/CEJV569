package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.EffectHistoryOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

public class EffectHistoryDataController extends DataController implements EffectHistoryOperation {

    public EffectHistoryDataController() {
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
