package cejv569.medicationtracker.model.transactioninterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.model.dataobjects.UserData;

/**
 * UserTransaction is an interface subclass of DataTransaction.  It is implemented by the UserTransactions
 * class to process requests from the model layer. More specifically, it is used to communicate
 * database requests from the following classes:  AccountOperation, LoginOperation
 * and SignupOperation.
 * The UserTransactions class executes the
 * prepared statement query that stores the user account info to the database.
 */
public interface UserTransaction extends DataTransaction{

   /**
    * getUserName is called by the SignupOperation instance to return a record from the
    * users table using the user name as parameter.  If a record is found by the UserTransactions object, a
    * return value of true is sent back.  This will prevent the account data to be saved as
    * the user name field in the data needs to be unique, hence not already registered in the
    * database.  If the user name is not found, a value of false is returned and the user account
    * signup information can be created in the database by the UserTransactions class.
    * @param userName - String type - represents the user name entered by the user at sign up in the
    *                               sign up form (view layer).
    * @return  boolean type -       returns true if a record was found for that user name in the users table.
    *                               Returns false if no record was found.
    * @throws OperationFailureException - Exception thrown if either there is a runtime
    *                                     error that occurs or either getUserName or createData
    *                                     transactions/requests throw an error at the database
    *                                     layer, errors which require the application to shut down
    */
   boolean getUserName(String userName) throws OperationFailureException;

   /**
    * getUserPassword is implemented by UserTransactions to return user and password information
    * from the users table to the model layer, given a user name entered by the user at login.
    * The function returns a User interface type object which contains the userid, the user name and the
    * password for that user name retrieved by the prepared statement( if a record is found).
    * The model layer uses this User data to verify if the user name exists and if so, if the
    * password entered at login matches the one stored in the database for that user.
    * @param userName - String type - is the user name entered by the user at login, in the login
    *                                  form.
    * @return           User interface type -   is used to return all
    *                                       user data that is requested from the database, users
    *                                       table.  If null is returned instead of a data object,
    *                                       no record was found in the database for that user name.
    * @throws OperationFailureException - Exception thrown if either there is a runtime
    *                                     error that occurs or either getUserName or createData
    *                                     transactions/requests throw an error at the database
    *                                     layer, errors which require the application to shut down
    */
   User getUserAndPassword(String userName) throws OperationFailureException;

   /**
    * getData is called by the LoginOperation to obtain the user account data from the database
    * to be able to initialize the profile form, account section.  The information for the
    * account is returned via a User interface type object.
    * @param userId  - int type -  represents the user id / user record primary key from the users
    *                table obtained by an earlier call to getUserandPassword.  It is used by UserTransactions
    *                to run a prepared statement which retrieves the full user record information.
    * @return  User interface type - Used to transfer all the user information in the
    *                                                 record for this user id from the users table.
    * @throws OperationFailureException - Exception thrown if either there is a runtime
    *                                     error that occurs or either getUserName or createData
    *                                     transactions/requests throw an error at the database
    *                                     layer, errors which require the application to shut down
    */
   User getData(int userId) throws OperationFailureException;

   /**
    * createData is called by the SignupOperation to create a user record with
    * the information that the user entered in the sign up for.  This method is called if no prior
    * user name record is found for the user name entered.  SignupOperation passes the information
    * to be inserted into the users table via a data object that support the User interface.
    * signup form
    * @param data - User interface type - Used to transfer all the user information in the
    *                                                   record for this user id from the users table.
    *
    * @throws OperationFailureException - Exception thrown if either there is a runtime
    *                                     error that occurs or either getUserName or createData
    *                                       transactions/requests throw an error at the database
    *                                     layer, errors which require the application to shut down
    */
   void createData(User data) throws OperationFailureException;

   void updateData(User data) throws OperationFailureException;

}
