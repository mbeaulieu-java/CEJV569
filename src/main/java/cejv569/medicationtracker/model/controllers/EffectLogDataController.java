package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.LogEffectOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

public class EffectLogDataController extends DataController implements LogEffectOperation {

    public EffectLogDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //setters and getters

    public DataTransaction getTransaction() {
        return super.transaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
    }
}
