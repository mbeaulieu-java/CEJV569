package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.database.ResultSetColumnNamesByTransactionKey;
import cejv569.medicationtracker.database.SQLPropertiesTransactionKeys;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.dataobjects.UserData;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationTransaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MedicationTransactions extends DataTransactions implements MedicationTransaction {
    public MedicationTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }

    @Override
    public List<Ingredient> getIngredients() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, nameColName,medicinalColName;

        //retrieve the select ingredients query using the proper SQLTransactionKey
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
    public List<MedicationIngredients> getMedicationIngredients() throws OperationFailureException {
        return null;
    }

    @Override
    public List<Medication> getMedications() throws OperationFailureException {
        return null;
    }

    @Override
    public List<Format> getFormats() throws OperationFailureException {
        return null;
    }

    @Override
    public List<MeasurementUnit> getMeasurementUnits() throws OperationFailureException {
        return null;
    }

    @Override
    public void createMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {

    }

    @Override
    public void deleteMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {

    }

    @Override
    public void createMedication(Medication medication) throws OperationFailureException {

    }

    @Override
    public void updateMedication(Medication medication) throws OperationFailureException {

    }

    @Override
    public void deleteMedication(Medication medication) throws OperationFailureException {

    }
}
