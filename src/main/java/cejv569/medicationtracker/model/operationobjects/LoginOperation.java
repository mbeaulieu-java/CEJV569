package cejv569.medicationtracker.model.operationobjects;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.NoSuchUserNameException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.WrongPasswordException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;
import cejv569.medicationtracker.model.transactioninterfaces.UserTransaction;

/**
 *  LoginOperation is a model level controller (a sort of middle) layer and coordinates requests
 *  between the View (GUI) layer and the database layer.  As such, it implements requests from the
 *  View layer via the LoginOperation interface.  It receives the view request and then
 *  uses it's UserTransaction Interface property to communicate with the database data layer.
 *  This class receives the login user name and password from the login view and applies the
 *  business rules to the data.  That is, it sends a request to the data layer to obtain the
 *  username, userid and user password from the database.  It then processes this data to
 *  answer the request of the view by throwing different errors if the data entered by the user
 *  at login is different from that in the database.  If the data is correct, it returns the
 *  userid so the application can subsequently retrieve the user account data.  On successful
 *  login, the LoginController returns the account (user data) information to the login view
 *  so that the controls in the profileForm can be initialized with the account data.
 *
 */

public class LoginOperation extends Operation implements cejv569.medicationtracker.model.operationinterfaces.LoginOperation {

    //Attributes
    private UserTransaction userTransaction;
    private int userId;

    //Constructor

    /**
     * LoginOperation - the constructor calls ApplicationController..transactionFactory
     * passing in an instance of itself, as it is the ApplicationController's responsibility to
     * create the transaction object that implements the UserTransaction interface - UserTransactions -
     * which communicates directly with the database.
     */
    public LoginOperation() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //getters and setters

    public  UserTransaction getTransaction() {
        return this.userTransaction;
    }

    @Override
    public int getUserID() {
        return this.userId;
    }

    /*The transaction argument received is of the parent DataTransaction type, so
    an attribute of UserTransaction type is initialized with the transaction instance
    typecast to the child type, so that the controller can see functions specific to the
            UserTransaction interface.*/
    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
        this.userTransaction = (UserTransaction)transaction;
    }

    /**
     *  userandPasswordExists receives the user name and password entered at login by the user
     *  from the view controller and first validates if the user name can be found in the database by
     *  sending a request for the information to the data layer via the UserTransaction interface.
     *  If the database layer returns a null User (userData) instance, then no record was found for
     *  this username so the function throws a NoSuchUserNameException.
     *  If the user name and password is obtained from the database layer, the retrieved password
     *  is validated against the password received as an argument.  If the two don't match,
     *  a WrongPasswordException is thrown.  If all the information matches, then the userid (
     *  primary key for this record in the users table) is returned.
     *
     * @param userName - String - represents the user name entered by the user at login
     * @param password - String - represents the password entered by the user at login
     * @return userId - int - represents the primary key associated with the user record in the
     *                  users table.
     * @throws NoSuchUserNameException - custom exception thrown in the case where the user name
     *                                  provided by the user can't be found in the database.
     * @throws WrongPasswordException - custom exception thrown in the case where the password
     *                                    provided by the user doesn't match the one stored
     *                                         in the database for the user name provided.
     * @throws OperationFailureException - custom exception thrown in the case where a runtime
     *                                      error occurs or the application is unable to
     *                                      obtain data required for app processing.  In these
     *                                      cases, the application is not able to recover from
     *                                      the error and thus throws this exception so that
     *                                      the application can handle the process of closing the
     *                                      application with an appropriate message to the user.
     *
     */
    @Override
    public int userAndPasswordExists(String userName, String password)
            throws NoSuchUserNameException, WrongPasswordException,
            OperationFailureException {
        int userId = 0;
        User userData;

        try {
            //request user and password information for the username
            //from the database layer.
           userData =  getTransaction().getUserAndPassword(userName);
           //if null, the user name isn't found
           if (userData == null) {
               throw new NoSuchUserNameException("User name not in database.");
           } else {
               //if the user is found, then validate the password matching
               if (!userData.getPassword().equals(password)) {
                   throw new WrongPasswordException("Password does not match the one in database" +
                           "for this user name.");
               } else { // if everything matches, return the user id.
                   userId = userData.getId();
                   this.userId = userId;
               }
           }
           //Catch any OperationFailure Exception thrown by the getUserandPassword method
            //and pass it on to the Login View Controller to be handled.
        } catch (OperationFailureException e) {
            throw e;
        }

        //return the user id/ primary key in users table.
        return userId;
    }

    /**
     * getAccountData
     * @param userID - int - represents the userid obtained from the prior Login Validation
     *               operation.  It is used to retrieve the user's account information.
     * @return - User interface type can receive any object which supports the User interface
     * - contains the user account information.
     * @throws OperationFailureException - custom error thrown if a runtime error occurs or
     *                                      - information required for the proper functioning
     *                                      of the app can't be retrieved, requiring the app
     *                                      to shut down.
     */
    @Override
    public User getAccountData(int userID) throws OperationFailureException {
        User userData = null;

        try {

            //if the function receives an invalid userid from the view controller, ie. the userid
            //is 0, then the information cannot be retrieved requiring an exception be thrown.
            if (userID == 0){throw new OperationFailureException("UserID was not initialized and is 0");}

            //use the UserTransaction interface to request from the database layer the recordset
            //corresponding to the userid, via an object that support the User interface.
            userData = getTransaction().getData(userID);
            //if the userData is null, then the record couldn't be found, so throw an exception.
            if (userData == null) { throw new OperationFailureException("No user data record found in " +
                    "the database.");}
        } catch (OperationFailureException e) {
            throw e;
        }
        //return the User instance to the calling LoginController subclass.
        return userData;
    }


}
