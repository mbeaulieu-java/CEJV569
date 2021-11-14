package cejv569.medicationtracker.model.operationobjects;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureEffectOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

public class EffectOperation extends Operation implements ConfigureEffectOperation {

    public EffectOperation() {
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
