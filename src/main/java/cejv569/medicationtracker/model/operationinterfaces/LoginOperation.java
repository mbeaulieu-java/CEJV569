package cejv569.medicationtracker.model.operationinterfaces;

import cejv569.medicationtracker.exceptions.NoSuchUserNameException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.WrongPasswordException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;

/**
 * LoginOperation is a subclass of ViewOperation.  It interfaces the data requests between the
 * LoginController (view layer) and the LoginDataController(model layer), the later being the
 * class which implements it.  It is used to decouple the view and model layer.  The interface
 * is used to transfer User interface type instances containing the data to be validated by with
 * user name and password information contained in the database.
 */

public interface LoginOperation extends ViewOperation{

    /**
     * Implemented by the LoginDataController class to return the user id (primary key) from
     * the users table.
     * @return int type - the user id for the user that logged in to the app.
     */
    int getUserID();


    /**
     *   This function isplemented by the LoginDataController and is used in the controller
     *   to send a request to the database layer for user name and password information from the
     *   database layer based on the user name obtained from the user name entered by the user
     *   at login.  If no record for the user is found in the database, a NoSuchUserNameException
     *   is thrown and if the password, returned by the database, for an existing user name does not match
     *   that entered by the user at login, a WrongPasswordException is thrown.  If no exception
     *   is thrown the function returns the user id (primary key from users table)  to the calling client.
     * @param userName
     * @param password
     * @return int type - the user id for the user name record in the database.
     * @throws NoSuchUserNameException  - custom exception thrown if the user name provided by the user
     *                                  - doesn't exist in the the database.
     * @throws WrongPasswordException - custom exception thrown if the password provided by the user
     *                                    does not match the one registered in the database for this user name.
     * @throws OperationFailureException -      Custom Exception thrown if either there is a runtime
     *                                         error that occurs or either getUserName or createData
     *                                          transactions/requests throw an error at the database
     *                                         layer, errors which require the application to shut down.
     */
    int userAndPasswordExists(String userName, String password)
            throws NoSuchUserNameException,WrongPasswordException ,OperationFailureException;


    /**
     *  This method is implemented by LoginDataController to retrieve the user account information
     *  using the id retrieved from the user and password authorization process at login.
     * @param userID - int type - the userid/ primary key from the database for that user record
     * @return - User interface type -  it is used to initialize the view controls with
     * user account data retrieved from the database layer.
     * @throws OperationFailureException Custom Exception thrown if either there is a runtime
     *                                    error that occurs or either getUserName or createData
     *                                    transactions/requests throw an error at the database
     *                                    layer, errors which require the application to shut down.
     */
   User getAccountData(int userID) throws OperationFailureException;


}
