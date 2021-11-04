package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.dataobjects.ContactsData;
import cejv569.medicationtracker.model.operationinterfaces.ContactOperation;
import cejv569.medicationtracker.model.transactioninterfaces.ContactTransaction;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;
import cejv569.medicationtracker.view.viewdata.ContactData;

/**
 *      ContactDataController is a child class of DataController.  It implements the
 *      ContactOperation interface through which it receives
 *      requests from the ContactController (view layer). It then applies any business rules to the
 *      data (data validation) if required.  After which it transfers a request to the proper
 *      transaction object via it's transaction interface instance (of type ContactTransaction).
 *      This transaction object represents the database/data layer which processes post requests
 *      to store emails, sent by people to the company (fictional), to the database.
 */

public class ContactDataController extends DataController implements ContactOperation {

    //Attributes
    private ContactTransaction contactTransaction;

    /**
     *  Send an instance of itself to the ApplicationController transactionfactory method
     *  so that it can instantiate the transaction object which implements the ContactTransaction
     *  interface (ContactTransactions class).
     */
    public ContactDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //Setters and Getters
    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
        this.contactTransaction = (ContactTransaction) transaction;
    }

    public ContactTransaction getTransaction() {
        return contactTransaction;
    }

    /**
     *  postData implements a request from the view layer to post the email contact information
     *  to the database.  It creates a new data object of type ContactsData and transfers the
     *  data from the view object (ContactData) to this data object instance.  It then
     *  sends the data via a transaction request (createData()) to the database layer so that the data
     *  gets created in the database.
     *
     * @param data - ContactData type - represents a ViewData subclass used to transfer email contact
     *                                  info obtained from the user via the contact view.
     * @return boolean type - returns true if the postData method and the createData requests
     *                          complete successfully, or false if they don't.
     * @throws OperationFailureException -         Custom Exception thrown if either there is a runtime
     *                                            error that occurs or either getUserName or createData
     *                                            transactions/requests throw an error at the database
     *                                            layer, errors which require the application to shut down.
     */
    @Override
    public boolean postData(ContactData data) throws OperationFailureException {

        //set the post to unsuccessful by default
        boolean successful = false;

        //create the data object and transfer the data from the view object to it, then send
        //the information to the database layer via the transaction request createData().
        try {
            getTransaction().createData(new ContactsData(
                    data.getId(),
                    data.getFullName(),
                    data.getMessage(),
                    data.getDate(),
                    data.getEmail()));

            //if no errors are thrown, the operation was successful.
            successful = true;

        //catch and throw any exceptions thrown by the database layer and pass it on
        //    to the view layer to be caught and processed.
        }catch (OperationFailureException e) {
            throw e;
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return successful;
    }
}
