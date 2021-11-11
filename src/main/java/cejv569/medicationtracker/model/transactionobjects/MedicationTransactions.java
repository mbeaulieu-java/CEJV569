package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.database.ResultSetColumnNamesByTransactionKey;
import cejv569.medicationtracker.database.SQLPropertiesTransactionKeys;
import cejv569.medicationtracker.exceptions.MedicationAlreadyAddedException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.dataobjects.*;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationTransaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicationTransactions extends DataTransactions implements MedicationTransaction {
    public MedicationTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }

    @Override
    public List<Ingredient> getIngredients() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, nameColName, medicinalColName;
        List<Ingredient> ingredientsList = new ArrayList<>();

        //retrieve the select ingredients query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .READ_INGREDIENTS_INFO.tKey);


            //get the utilities class that contains the column names for this query &
            //get the query column values from the resultSet
            idColName = ResultSetColumnNamesByTransactionKey.Ingredients_Info.ID.columnName;
            nameColName = ResultSetColumnNamesByTransactionKey.Ingredients_Info.NAME.columnName;
            medicinalColName = ResultSetColumnNamesByTransactionKey.Ingredients_Info.MEDICINAL.columnName;

            //excute the Select query
            resultSet = theStatement.executeQuery();

            //read all the ingredients records from the resultset into the ingredients data object
            //list
            while (resultSet.next()) {

                //create the UserData object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                ingredientsList.add(new IngredientData(
                        resultSet.getInt(idColName),
                        resultSet.getString(nameColName),
                        resultSet.getBoolean(medicinalColName)));
            }

        } catch (OperationFailureException | SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        //return null if no records are retrieved or the ingredients list
        return (ingredientsList.isEmpty() ? null : ingredientsList);
    }

    @Override
    public List<MedicationIngredients> getMedicationIngredients(int userId) throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, medicationIdColName, ingredientIdColName, nameColName;
        List<MedicationIngredients> medicationIngredientsList = new ArrayList<>();

        //retrieve the select ingredients query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .READ_MEDICATION_INGREDIENTS_INFO.tKey);


            //get the utilities class that contains the column names for this query &
            //get the query column values from the resultSet
            idColName = ResultSetColumnNamesByTransactionKey
                    .Medication_Ingredients_Info.ID.columnName;
            nameColName = ResultSetColumnNamesByTransactionKey
                    .Medication_Ingredients_Info.NAME.columnName;
            medicationIdColName = ResultSetColumnNamesByTransactionKey
                    .Medication_Ingredients_Info.MEDICATION_ID.columnName;
            ingredientIdColName = ResultSetColumnNamesByTransactionKey
                    .Medication_Ingredients_Info.INGREDIENT_ID.columnName;

            theStatement.setInt(1, userId);

            //excute the Select query
            resultSet = theStatement.executeQuery();

            //read all the records from the resultset into the medication_ingredients data object
            //list
            while (resultSet.next()) {

                //create the MedicationIngredients object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                medicationIngredientsList.add(new MedicationIngredientsData(
                        resultSet.getInt(idColName),
                        resultSet.getInt(medicationIdColName),
                        resultSet.getInt(ingredientIdColName),
                        resultSet.getString(nameColName)));
            }

        } catch (OperationFailureException | SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        //return the medication ingredients list  or null, if no records are found.
        return medicationIngredientsList;
    }

    @Override
    public List<Medication> getMedications(int userId) throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, formatIdColName, measurementIdColName, userIdColName,
                brandNameColName, genericNameColName;
        List<Medication> medicationsList = new ArrayList<>();

        //retrieve the select ingredients query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys.READ_MEDICATIONS_INFO.tKey);


            //get the utilities class that contains the column names for this query &
            //get the query column values from the resultSet
            idColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.ID.columnName;
            formatIdColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.FORMAT_ID.columnName;
            measurementIdColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.MEASUREMENT_ID.columnName;
            userIdColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.USER_ID.columnName;
            genericNameColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.GENERIC_NAME.columnName;
            brandNameColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.BRAND_NAME.columnName;

            theStatement.setInt(1, userId);

            //excute the Select query
            resultSet = theStatement.executeQuery();

            //read all the records from the resultset into the medication data object
            //list
            while (resultSet.next()) {

                //create the medication object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                medicationsList.add(new MedicationData(
                        resultSet.getInt(idColName),
                        resultSet.getInt(formatIdColName),
                        resultSet.getInt(measurementIdColName),
                        resultSet.getInt(userIdColName),
                        resultSet.getString(brandNameColName),
                        resultSet.getString(genericNameColName)));
            }

        } catch (OperationFailureException | SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        //return the formats list  or null, if no records are found.
        
        return medicationsList;
    }

    @Override
    public List<Format> getFormats() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, labelColName;
        List<Format> formatsList = new ArrayList<>();

        //retrieve the select ingredients query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys.READ_FORMATS_INFO.tKey);


            //get the utilities class that contains the column names for this query &
            //get the query column values from the resultSet
            idColName = ResultSetColumnNamesByTransactionKey
                    .Formats_Info.ID.columnName;
            labelColName = ResultSetColumnNamesByTransactionKey
                    .Formats_Info.LABEL.columnName;

            //excute the Select query
            resultSet = theStatement.executeQuery();

            //read all the records from the resultset into the format data object
            //list
            while (resultSet.next()) {

                //create the format object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                formatsList.add(new FormatData(
                        resultSet.getInt(idColName),
                        resultSet.getString(labelColName)));
            }

        } catch (OperationFailureException | SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        //return the formats list  or null, if no records are found.
        return (formatsList.isEmpty() ? null : formatsList);
    }

    @Override
    public List<MeasurementUnit> getMeasurementUnits() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, unitNameColName;
        List<MeasurementUnit> measurementUnitsList = new ArrayList<>();

        //retrieve the select ingredients query using the proper SQLTransactionKey
        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys.READ_MEASUREMENT_UNITS_INFO.tKey);


            //get the utilities class that contains the column names for this query &
            //get the query column values from the resultSet
            idColName = ResultSetColumnNamesByTransactionKey
                    .Formats_Info.ID.columnName;
            unitNameColName = ResultSetColumnNamesByTransactionKey
                    .Measurement_Units_Info.UNIT_NAME.columnName;

            //excute the Select query
            resultSet = theStatement.executeQuery();

            //read all the records from the resultset into the measurement unit data object
            //list
            while (resultSet.next()) {

                //create the measurement unit object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                measurementUnitsList.add(new MeasurementUnitData(
                        resultSet.getInt(idColName),
                        resultSet.getString(unitNameColName)));
            }

        } catch (OperationFailureException | SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        //return the formats list  or null, if no records are found.
        return (measurementUnitsList.isEmpty() ? null : measurementUnitsList);
    }

    @Override
    public void createMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {
        PreparedStatement theStatement = null;

        if (!medicationIngredients.isEmpty()) {
            //retrieve the insert query using the proper SQLTransactionKey
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .CREATE_MEDICATION_INGREDIENTS_INFO.tKey);
        }


        try {
            for (MedicationIngredients ing : medicationIngredients) {

                //clear the parameters for the next query to be run
                theStatement.clearParameters();

                // set the parameters for the insertion prepared statement
                theStatement.setInt(1, ing.getMedicationId());
                theStatement.setInt(2, ing.getIngredientId());

                //excute the insert
                theStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public void deleteMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {
        PreparedStatement theStatement = null;

        if (!medicationIngredients.isEmpty()) {
            //retrieve the delete query using the proper SQLTransactionKey
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .DELETE_MEDICATION_INGREDIENTS_INFO.tKey);
        }


        try {
            for (MedicationIngredients ing : medicationIngredients) {

                //clear the parameters for the next query to be run
                theStatement.clearParameters();

                // set the parameters for the delete prepared statement
                theStatement.setInt(1, ing.getId());

                //excute the delete
                theStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public void createMedication(Medication medication) throws OperationFailureException {
        PreparedStatement theStatement = null;

        //retrieve the insert query using the proper SQLTransactionKey
        theStatement = getDatasource()
                .getSQLStatement(
                        SQLPropertiesTransactionKeys
                                .SQLTransactionKeys
                                .CREATE_MEDICATION_INFO.tKey);

        try {
            // set the parameters for the insert prepared statement
            theStatement.setInt(1, medication.getFormatId());
            theStatement.setInt(2, medication.getMeasurementId());
            theStatement.setInt(3, medication.getUserId());
            theStatement.setString(4, medication.getBrandName());
            theStatement.setString(5, medication.getGenericName());

            //excute the insert
            theStatement.executeUpdate();

            //clear the parameters for the next query to be run
            theStatement.clearParameters();

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public void updateMedication(Medication medication) throws OperationFailureException {
        PreparedStatement theStatement = null;

        //retrieve the update query using the proper SQLTransactionKey
        theStatement = getDatasource()
                .getSQLStatement(
                        SQLPropertiesTransactionKeys
                                .SQLTransactionKeys
                                .UPDATE_MEDICATION_INFO.tKey);

        try {
            // set the parameters for the update prepared statement
            theStatement.setInt(1, medication.getFormatId());
            theStatement.setInt(2, medication.getMeasurementId());
            theStatement.setInt(3, medication.getUserId());
            theStatement.setString(4, medication.getBrandName());
            theStatement.setString(5, medication.getGenericName());
            theStatement.setInt(6, medication.getId());

            //excute the update
            theStatement.executeUpdate();

            //clear the parameters for the next query to be run
            theStatement.clearParameters();

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public boolean medicationAlreadyExists(Medication medication) throws OperationFailureException {
        PreparedStatement theStatement;
        ResultSet resultSet;
        boolean medicationExists = true;

        //use the getSQLStatement to retrieve the right query to validate if the medication record to add
        //is already in the database, with another primary key but with identical information.

        try {
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .VERIFY_UNIQUE_MEDICATION.tKey);

            //set the parameters that must be unique and so need to be validate to see
            //if they already exist in the database.
            theStatement.setInt(1,medication.getUserId());
            theStatement.setInt(2,medication.getFormatId());
            theStatement.setInt(3,medication.getMeasurementId());
            theStatement.setString(4, medication.getBrandName());
            theStatement.setString(5, medication.getGenericName());
            theStatement.setInt(6,medication.getId());

            resultSet = theStatement.executeQuery();

            //if next() returns true, then the select statement returned a record with the
            //same user name hence the username already exists in the database.
            medicationExists = resultSet.next();

            theStatement.clearParameters();

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        }
        return medicationExists;
    }
}