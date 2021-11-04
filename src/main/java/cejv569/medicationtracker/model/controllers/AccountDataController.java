package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.AccountOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;
import cejv569.medicationtracker.model.transactioninterfaces.UserTransaction;

public class AccountDataController extends DataController implements AccountOperation {
    public UserTransaction userTransaction;

    public AccountDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //getters and setters

    public UserTransaction getTransaction() {
        return this.userTransaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {

        super.transaction = transaction;
        this.userTransaction = (UserTransaction)transaction;
    }


}
