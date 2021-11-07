package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.UserAlreadyExistsException;
import cejv569.medicationtracker.model.datainterfaces.User;
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

    @Override
    public boolean putData(User data) throws OperationFailureException{

        //set the put/update to unsuccessful by default
        boolean successful = false;

        try {
            getTransaction().updateData(data);
            successful = true;
        } catch (OperationFailureException e) {
            throw e;
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        //return if the post operation was successful.
        return successful;
    }

}
