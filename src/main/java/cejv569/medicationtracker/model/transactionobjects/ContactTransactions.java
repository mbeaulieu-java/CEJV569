package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.database.SQLPropertiesTransactionKeys;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.Contact;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.model.dataobjects.ContactData;
import cejv569.medicationtracker.model.dataobjects.UserData;
import cejv569.medicationtracker.model.transactioninterfaces.ContactTransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */

public class ContactTransactions extends DataTransactions implements ContactTransaction {

    //Constructor
    public ContactTransactions(MedTrackDatasource datasource) {
        //instance of MedTrackDatasource class which returns the prepared statements to be executed
        //by ContactTransactions.
        super(datasource);

    }

    //Getters and Setters
    @Override
    public MedTrackDatasource getDatasource() {
        return super.getDatasource();
    }

    @Override
    public void setDatasource(MedTrackDatasource datasource) {
        super.setDatasource(datasource);
    }

    /**
     *      createData receives a Contact interface type object with the information from the email
     *      sent by a user, to be inserted into the contacts table in the database. The method
     *      first retrieves the prepared statement from the MedTrackDatasource instance using the
     *      associated SQLTransactionKey.  If the prepared statement is retrieved successfully by
     *      the datasource instance, then the prepared statement is executed to perform the insert
     *      in the database contacts table.  If any exception occurs, a OperationFailure is thrown.
     * @param data Contact interface type - data object containing the contact email information for the record
     *                                  to be inserted into the database.
     * @throws OperationFailureException  - Exception thrown if either there is a runtime
     *                                  error that occurs or either getUserName or createData
     *                                  transactions/requests throw an error at the database
     *                                  layer, errors which require the application to shut down
     */
    @Override
    public void createData(Contact data) throws OperationFailureException {

        PreparedStatement theStatement;
        //retrieve the insert user query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .INSERT_CONTACT.tKey);

            // set the parameters for the insertion prepared statement
            theStatement.setString(1,data.getFullName());
            theStatement.setString(2,data.getEmail());
            theStatement.setString(3,data.getMessage());

            //excute the insert via query execution
            theStatement.executeUpdate();


            theStatement.clearParameters();
        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        }
    }
}
