package cejv569.medicationtracker.model.operationinterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.UserAlreadyExistsException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;

/**
 * SignupOperation is a subclass of ViewOperation.  It interfaces the data requests between the
 * SignupController (view layer) and the SignupDataController(model layer), the later being the
 * class which implements it.  It is used to decouple the view and model layer.  The interface
 * is used to transfer AccountObservableData type instances containing the minimum account data to be saved to the
 * database upon user sign up.
 */


public interface SignupOperation extends ViewOperation {

    /**
     * postData is implemented by SignupDataController and transfers the signup user data
     * to the database layer via an AccountObservableData ViewObservableData subclass data object, to be saved to
     * the database.  The SignupDataController validates if the user name already exists in the database
     * before the data is saved to the database layer because the user name must be unique in the database.
     * If the user name already exists in the database, a UserAlreadyExistsException is thrown.
     * @param data AccountObservableData type - data object containing the user account information entered
     *             at Sign up by the user.
     * @return boolean type - returns true if the postData request was successfully completed
     *                          by both the SignupDataController and the database layer.
     * @throws UserAlreadyExistsException -         custom exception thrown if the data can't be posted
     *                                               because the user name already exists in the
     *                                                database.
     * @throws OperationFailureException            Exception thrown if either there is a runtime
     *                                              error that occurs or either getUserName or createData
     *                                              transactions/requests throw an error at the database
     *                                              layer, errors which require the application to shut down.
     */
    boolean postData(User data) throws UserAlreadyExistsException, OperationFailureException;
}
