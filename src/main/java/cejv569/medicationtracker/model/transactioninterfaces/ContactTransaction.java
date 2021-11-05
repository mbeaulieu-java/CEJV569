package cejv569.medicationtracker.model.transactioninterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.Contact;
import cejv569.medicationtracker.model.dataobjects.ContactData;

/**
 * ContactTransaction is a subclass of DataTransaction.  It is implemented by the ContactTransactions
 * class to process requests from the model layer. The ContactTransactions class executes the
 * prepared statement query that stores the contact email info to the database.
 */
public interface ContactTransaction extends DataTransaction{

    /**
     *  createData is called from the model layer and passed a Contact interface type object containing
     *  the contacts information to be stored in the database.  The record is then created
     *  by the ContactTransactions class via the insert prepared statement query.
     * @param data Contact interface type - data object containing the contact email information for the record
     *                                  to be inserted into the database.
     * @throws OperationFailureException                 Exception thrown if either there is a runtime
     *                                                   error that occurs or either getUserName or createData
     *                                                   transactions/requests throw an error at the database
     *                                                   layer, errors which require the application to shut down
     */
    void createData(Contact data) throws OperationFailureException;
}
