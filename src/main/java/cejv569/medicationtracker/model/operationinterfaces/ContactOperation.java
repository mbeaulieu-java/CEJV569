package cejv569.medicationtracker.model.operationinterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.Contact;
import cejv569.medicationtracker.view.viewdata.ContactObservableData;

/**
 * ContactOperation is a subclass of ViewOperation.  It interfaces the data requests between the
 * ContactController (view layer) and the ContactDataController(model layer), the later being the
 * class which implements it.  It is used to decouple the view and model layer.  The interface
 * is used to transfer ContactObservableData type instances.
 */
public interface ContactOperation extends ViewOperation{

    /**
     * postData is called by the ContactController (view) layer to transfer the
     * contact information data for emails sent to the fictional company.  The data is transfered
     * to the model layer which applies any business rules and then send it to the database layer
     * to be posted to the database.
     * @param data - ContactObservableData type - represents the viewData subclass containing the information
     *                                  from email transactions performed in the Contact form. Info like
     *                                  fullname, email and message.
     * @return boolean type -       true if the transaction was successful and false if it wasn't
     * @throws OperationFailureException -  Custom Exception thrown if either there is a runtime
     *                                      error that occurs or either getUserName or createData
     *                                      transactions/requests throw an error at the database
     *                                      layer, errors which require the application to shut down.
     */
    boolean postData(Contact data) throws OperationFailureException;
}
