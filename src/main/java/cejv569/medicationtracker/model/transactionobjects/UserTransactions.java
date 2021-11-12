package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.database.ResultSetColumnNamesByTransactionKey;
import cejv569.medicationtracker.database.SQLPropertiesTransactionKeys;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.model.dataobjects.UserData;
import cejv569.medicationtracker.model.transactioninterfaces.UserTransaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 *
 */

public class UserTransactions extends DataTransactions implements UserTransaction {
    //constructor
    public UserTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }


    /**
     *  getUserName calls its datasource instance to retrieve the select query which returns a record
     *  from the database given a user name as a parameter.  This is done using the validate_username
     *  transaction key, to specify the sql file to use, given the key-value pairs to be found in
     *  transactionsqlmatch.properties.  It then executes the returned prepared statement.  If a record
     *  is returned by the prepared statement, the user name is in the database and the
     *  function returns true, that the user name exists in the users table.
     * @param userName - String type - represents the user name entered by the user at sign up in the
     *                               sign up form (view layer).
     * @return  boolean type - returns true if a record is found by the query in the users table
     *                          for the user name received as argument. Returns false if no record
     *                          is found.
     * @throws OperationFailureException - Exception thrown if either there is a runtime
     *                                      error that occurs or either getUserName or createData
     *                                  transactions/requests throw an error at the database
     *                                  layer, errors which require the application to shut down
     */
    @Override
    public boolean getUserName(String userName) throws OperationFailureException{
        PreparedStatement theStatement;
        ResultSet resultSet;
        boolean nameExists = false;

        //use the getSQLStatement to retrieve the right query to validate the user id, according
        // to the SQLTransactionKey.  Initialize it with the username parameter and run it.
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .VALIDATE_USERNAME.tKey);

            theStatement.setString(1, userName);
            resultSet = theStatement.executeQuery();

            //if next() returns true, then the select statement returned a record with the
            //same user name hence the username already exists in the database.
            nameExists = resultSet.next();

            theStatement.clearParameters();

        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        return nameExists;
    }

    /**
     *  createData processes requests to create user records in the users table.  These are created
     *  at sign up in the application.  The method receives the data to be inserted via a
     *  User interface type object.  First, it uses it's datasource instance to obtain the prepared statement
     *  for the given SQLTransaction key.  It then initializes the insert query with the parameters
     *  containing the data to be inserted into the users table.  After it executes the prepared statement.
     *  If any exception occurs during the process a OperationFailureException is thrown.
     * @param data - User interface type - Used to transfer all the user information in the
     *                                                   record for this user id from the users table.
     *
     * @throws OperationFailureException - Exception thrown if either there is a runtime
     *                                      error that occurs or either getUserName or createData
     *                                      transactions/requests throw an error at the database
     *                                      layer, errors which require the application to shut down
     */
    @Override
    public void createData(User data) throws OperationFailureException{
        int rowCount = 0;
        PreparedStatement theStatement;
        //retrieve the insert user query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .CREATE_USER_INFO.tKey);

            // set the parameters for the insertion prepared statement
            theStatement.setString(1,data.getFirstName());
            theStatement.setString(2,data.getLastName());
            theStatement.setString(3,data.getUserName());
            theStatement.setString(4,data.getPassword());
            theStatement.setString(5,data.getEmail());
            theStatement.setString(6,data.getTelephone());

            //excute the insert
            rowCount = theStatement.executeUpdate();
            if (rowCount == 0) {
                throw new SQLSyntaxErrorException("The insert for the user account information failed." +
                        "Verify the SQL statement : " + SQLPropertiesTransactionKeys
                        .SQLTransactionKeys
                        .CREATE_USER_INFO.tKey);
            }
            theStatement.clearParameters();
        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
    }

    /**
     *  getUserandPassword processes a request from the model layer by returning a user record
     *  from the users table, given the user name argument provided.  It uses it's datasource to
     *  retrieve the appropriate prepared statement select query, executes the query to obtain the record.
     *  If a record is found, it returns the primary key (user id) for the record in the users table
     *  along with the user name and the password.  If no record is found the function returns null,
     *  or else a new UserData object (which implements the User interface) is created,
     *  it's initialized with the information and returned to the
     *  calling model layer.  Any exceptions that occur cause a OperationFailureException to be
     *  thrown.
     * @param userName - String type - is the user name entered by the user at login, in the login
     *                                  form.
     * @return - User interface type  - is used to return user information, retrieved
     * from the users table by the prepared statement, to the calling model layer.
     * @throws OperationFailureException - Exception thrown if either there is a runtime
     *                                      error that occurs or either getUserName or createData
     *                                      transactions/requests throw an error at the database
     *                                      layer, errors which require the application to shut down
     */

    @Override
    public User getUserAndPassword(String userName) throws OperationFailureException {
        User userData = null;
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName,userColName, passwordColName;

        //retrieve the insert user query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .VALIDATE_USERNAME.tKey);

            // set the parameters for the select prepared statement
            theStatement.setString(1,userName);

            //excute the Select query
            resultSet = theStatement.executeQuery();

            if (resultSet.next()) {

                //get the utilities class that contains the column names for this query &
                //get the query column values from the resultSet
                idColName = ResultSetColumnNamesByTransactionKey.Validate_UserName.ID.columnName;
                userColName = ResultSetColumnNamesByTransactionKey.Validate_UserName.USER_NAME.columnName;
                passwordColName = ResultSetColumnNamesByTransactionKey.Validate_UserName.USER_PASSWORD.columnName;

                //create a new UserData instance to return the data retrieved by the prepared
                //statement.  Initialize the data object by retrieving the field values from
                //the resultset.
                userData =
                        new UserData(
                        resultSet.getInt(idColName),
                                "",
                                "",
                                resultSet.getString(userColName),
                                resultSet.getString(passwordColName),
                                "",
                                "");
            }
            theStatement.clearParameters();
        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        return userData;
    }

    /**
     *  getData executes a select statement via the prepared statement retrieved using it's datasource
     *  instance.  It retrieves the users table data for the provided primary key for that user record
     *  in the table.  If a record is retrieved, it creates a new UserData object (which implements the User interface)
     *  to return the
     *  requested data.  If no record is retrieved, a null value is returned.  Any exceptions cause a
     *  OperationFailureException to be thrown.
     * @param userId  - int type -  represents the user id / user record primary key from the users
     *                table obtained by an earlier call to getUserandPassword.  It is used by UserTransactions
     *                to run a prepared statement which retrieves the full user record information.
     * @return        User interface type - is used to return user data retrieved by the
     * executed prepared statement from the users table.  Returns null if no data can be retrieved for the given
     * userid, which is the record primary key in the users table.
     * @throws OperationFailureException - Exception thrown if either there is a runtime
     *                                     error that occurs or either getUserName or createData
     *                                     transactions/requests throw an error at the database
     *                                     layer, errors which require the application to shut down
     */
    @Override
    public User getData(int userId) throws OperationFailureException {
        User data = null;
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, fNameColName,lNameColName, userColName, passwordColName,
                emailColName,phoneColName;

        //retrieve the select user query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .READ_USER_INFO.tKey);

            // set the parameters for the select prepared statement
            theStatement.setInt(1,userId);

            //excute the Select query
            resultSet = theStatement.executeQuery();
            if (resultSet.next()) {
                //get the utilities class that contains the column names for this query &
                //get the query column values from the resultSet
                idColName = ResultSetColumnNamesByTransactionKey.User_Info.ID.columnName;
                fNameColName = ResultSetColumnNamesByTransactionKey.User_Info.FIRST_NAME.columnName;
                lNameColName = ResultSetColumnNamesByTransactionKey.User_Info.LAST_NAME.columnName;
                userColName = ResultSetColumnNamesByTransactionKey.Validate_UserName.USER_NAME.columnName;
                passwordColName = ResultSetColumnNamesByTransactionKey.Validate_UserName.USER_PASSWORD.columnName;
                emailColName = ResultSetColumnNamesByTransactionKey.User_Info.EMAIL.columnName;
                phoneColName = ResultSetColumnNamesByTransactionKey.User_Info.TELEPHONE.columnName;

                //create the UserData object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                data = new UserData(
                                resultSet.getInt(idColName),
                                resultSet.getString(fNameColName),
                                resultSet.getString(lNameColName),
                                resultSet.getString(userColName),
                                resultSet.getString(passwordColName),
                                resultSet.getString(emailColName),
                                resultSet.getString(phoneColName));
            }
            theStatement.clearParameters();
        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        //returned to data object with the data or null, if no record was found.
       return data;
    }

    @Override
    public void updateData(User data) throws OperationFailureException {
        PreparedStatement theStatement;
        int rowCount = 0;
        //retrieve the update user query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .UPDATE_USER_INFO.tKey);

            // set the parameters for the update prepared statement
            theStatement.setString(1,data.getFirstName());
            theStatement.setString(2,data.getLastName());
            theStatement.setString(3,data.getUserName());
            theStatement.setString(4,data.getPassword());
            theStatement.setString(5,data.getEmail());
            theStatement.setString(6,data.getTelephone());
            theStatement.setInt(7,data.getId());

            //excute the update
            rowCount = theStatement.executeUpdate();

            if (rowCount == 0) {
                throw new SQLSyntaxErrorException("The update for the user account information failed." +
                        "Verify the SQL statement : " + SQLPropertiesTransactionKeys
                        .SQLTransactionKeys
                        .UPDATE_USER_INFO.tKey);
            }

            theStatement.clearParameters();
        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
    }
}
