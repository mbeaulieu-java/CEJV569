package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.UserAlreadyExistsException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.model.dataobjects.UserData;
import cejv569.medicationtracker.model.operationinterfaces.SignupOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;
import cejv569.medicationtracker.model.transactioninterfaces.UserTransaction;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;

/**
 *  SignupDataController implements the SignupOperation interface through which it receives
 *  requests from the SignupController (view layer). It then applies any business rules to the
 *  data (data validation) required.  After which it transfers a request to the proper
 *  transaction object via it's transaction interface instance (of type UserTransaction).
 *  This transaction object represents the database/data layer which returns it data from
 *  the database.
 */

public class SignupDataController extends DataController implements SignupOperation {

    //Attributes
    private UserTransaction userTransaction;

    /**
     *SignupDataController - the constructor calls ApplicationController..transactionFactory
     * passing in an instance of itself, as it is the ApplicationController's responsibility to
     * create the transaction object that implements the UserTransaction interface - UserTransactions -
     * which communicates directly with the database.
     */
    public SignupDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //setters and getters

    public UserTransaction getTransaction() {
        return this.userTransaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
        this.userTransaction = (UserTransaction)transaction;
    }

    /**
     *  postData is used to post the user information for a new user upon their sign up.  It
     *  validates if the user name already exists in the database (as each user name must
     *  be unique for login security logic).  If it does, it throws a UserAlreadyExistsException
     *  to be caught by the Signup view controller.  If the user name doesn't already exist
     *  in the database, then it sends the post request to the database layer via a UserData instance
     *  object.  It returns whether the operation was successful.
     * @param data - AccountObservableData type - represents the data in an observable format, obtained
     *             from the data input by the user into the view.
     * @return - boolean type - returns true if the postData request completed successfully or
     *                          not.
     * @throws UserAlreadyExistsException - custom exception thrown if the data can't be posted
     *                                      because the user name already exists in the
     *                                      database.
     * @throws OperationFailureException  - Exception thrown if either there is a runtime
     *                                      error that occurs or either getUserName or createData
     *                                      transactions/requests throw an error at the database
     *                                      layer, errors which require the application to shut down.
     */
    @Override
    public boolean postData(User data) throws UserAlreadyExistsException, OperationFailureException {

        //set the post to unsuccessful by default
        boolean successful = false;

        //verify if the username in the new record already exists in the database, as the
        //username must be a unique value.
        if (getTransaction().getUserName(data.getUserName())) {
            //throw an error that the user already exists
            throw new UserAlreadyExistsException("username already exists.");
        } else {
            //if the username doesn't already exist, then transfer the data to the
            //data object of type UserData and send it via createData to the database layer
            //so that a new user record can be created in the database.

            getTransaction().createData(new UserData(
                    data.getId(),
                    data.getFirstName(),
                    data.getLastName(),
                    data.getUserName(),
                    data.getPassword(),
                    data.getEmail(),
                    data.getTelephone()
            ));
            successful = true;
        }
        //return if the post operation was successful.
        return successful;
    }
}
