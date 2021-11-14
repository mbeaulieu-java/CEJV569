package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.database.ResultSetColumnNamesByTransactionKey;
import cejv569.medicationtracker.database.SQLPropertiesTransactionKeys;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.dataobjects.*;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationTransaction;

import java.sql.*;
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
    public List<MedicationIngredients> getMedicationIngredients(Medication medicationParameters) throws OperationFailureException {
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

            theStatement.setInt(1, medicationParameters.getUserId());
            theStatement.setInt(2,medicationParameters.getId());

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
                nameColName;
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
            nameColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.NAME.columnName;

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
                        resultSet.getString(nameColName)));
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
    public List<MedicationIngredients> createMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {
        PreparedStatement theStatement = null;
        int rowCount = 0;
        int medIngKey = 0;
        ResultSet generatedKey = null;

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
                rowCount = theStatement.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLSyntaxErrorException("The insert for the medication ingredients failed." +
                            "Verify the SQL statement : " + SQLPropertiesTransactionKeys
                            .SQLTransactionKeys
                            .CREATE_MEDICATION_INGREDIENTS_INFO.tKey);
                } else {
                    generatedKey = theStatement.getGeneratedKeys();
                    if(generatedKey.next()) {
                        medIngKey = generatedKey.getInt(1);
                    }

                    ing.setId(medIngKey);
                }
            }

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return medicationIngredients;
    }

    @Override
    public void deleteMedicationIngredients(MedicationIngredients medicationIngredient) throws OperationFailureException {
        PreparedStatement theStatement = null;
        int rowCount = 0;

        if (medicationIngredient != null) {
            //retrieve the delete query using the proper SQLTransactionKey
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .DELETE_MEDICATION_INGREDIENTS_INFO.tKey);
        }

        try {
            // set the parameters for the delete prepared statement
            theStatement.setInt(1, medicationIngredient.getId());

            //excute the delete
            rowCount = theStatement.executeUpdate();

            //for now, do not throw an error if no record was found to delete.  It's
            //possible that the record was removed from the list before it was added
            //to the database.
//            if (rowCount == 0) {
//                throw new SQLSyntaxErrorException("The delete for the medication ingredients failed." +
//                        "Verify the SQL statement : " + SQLPropertiesTransactionKeys
//                        .SQLTransactionKeys
//                        .DELETE_MEDICATION_INGREDIENTS_INFO.tKey);
//            }

            //clear the parameters for the next query to be run
            theStatement.clearParameters();

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public int createMedication(Medication medication) throws OperationFailureException {
        int rowCount = 0;
        int addedMedicationKey = 0;
        PreparedStatement theStatement = null;
        ResultSet generatedKeys;

        try {
            //retrieve the insert query using the proper SQLTransactionKey
           theStatement = getDatasource().getSQLStatement(SQLPropertiesTransactionKeys
                    .SQLTransactionKeys
                    .CREATE_MEDICATION_INFO.tKey);

            // set the parameters for the insert prepared statement
            theStatement.setInt(1, medication.getFormatId());
            theStatement.setInt(2, medication.getMeasurementId());
            theStatement.setInt(3, medication.getUserId());
            theStatement.setString(4, medication.getName());

            //excute the insert
            rowCount = theStatement.executeUpdate();

            if (rowCount == 0) {
                throw new SQLSyntaxErrorException("The create for the medication failed." +
                        "Verify the SQL statement : " + SQLPropertiesTransactionKeys
                        .SQLTransactionKeys
                        .CREATE_MEDICATION_INFO.tKey);
            } else {
                generatedKeys = theStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    addedMedicationKey = generatedKeys.getInt(1);
                }
            }
            //clear the parameters for the next query to be run
            theStatement.clearParameters();

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return addedMedicationKey;
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
            theStatement.setString(4, medication.getName());
            theStatement.setInt(5, medication.getId());
            theStatement.setInt(6,medication.getUserId());

            //excute the update
            theStatement.executeUpdate();

            //clear the parameters for the next query to be run
            theStatement.clearParameters();

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage() +
                    " QueryID: " + SQLPropertiesTransactionKeys
                    .SQLTransactionKeys
                    .UPDATE_MEDICATION_INFO.tKey);
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage() + " QueryId: "
                    + SQLPropertiesTransactionKeys
                    .SQLTransactionKeys
                    .UPDATE_MEDICATION_INFO.tKey );
        }
    }

    //Update Medication Ingredients
    @Override
    public List<MedicationIngredients> updateMedicationIngredients(List<MedicationIngredients> medicationIngredients)
            throws OperationFailureException {

        PreparedStatement theStatement = null;
        PreparedStatement validateMedicationIngredientExistence = null;

        int rowCount = 0;
        int medIngKey = 0;

        ResultSet generatedKey, ingredientExistsResultSet = null;

        //statement that executes the update
        if (!medicationIngredients.isEmpty()) {
            //retrieve the insert query using the proper SQLTransactionKey
            theStatement = getDatasource()
                    .getSQLStatement(
                            SQLPropertiesTransactionKeys
                                    .SQLTransactionKeys
                                    .CREATE_MEDICATION_INGREDIENTS_INFO.tKey);

            //statement that validates if the ingredient already exists
            validateMedicationIngredientExistence = getDatasource().getSQLStatement(
                    SQLPropertiesTransactionKeys
                            .SQLTransactionKeys
                            .FIND_MEDICATION_INGREDIENT.tKey);
        }

        try {
            for (MedicationIngredients ing : medicationIngredients) {

                validateMedicationIngredientExistence.clearParameters();
                validateMedicationIngredientExistence.setInt(1,ing.getId());
                validateMedicationIngredientExistence.setInt(2,ing.getIngredientId());
                validateMedicationIngredientExistence.setInt(3,ing.getMedicationId());

                ingredientExistsResultSet =
                validateMedicationIngredientExistence.executeQuery();

                if (ingredientExistsResultSet.next()) continue;

                //clear the parameters for the next query to be run
                theStatement.clearParameters();

                // set the parameters for the insertion prepared statement
                theStatement.setInt(1, ing.getMedicationId());
                theStatement.setInt(2, ing.getIngredientId());

                //excute the insert
                rowCount = theStatement.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLSyntaxErrorException("The insert for the medication ingredients failed." +
                            "Verify the SQL statement : " + SQLPropertiesTransactionKeys
                            .SQLTransactionKeys
                            .CREATE_MEDICATION_INGREDIENTS_INFO.tKey);
                } else {
                    generatedKey = theStatement.getGeneratedKeys();
                    if(generatedKey.next()) {
                        medIngKey = generatedKey.getInt(1);
                    }

                    ing.setId(medIngKey);
                }
            }

        } catch (SQLException e) {
            throw new OperationFailureException(e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return medicationIngredients;
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
            theStatement.setString(4, medication.getName().toLowerCase());
            theStatement.setInt(5,medication.getId());

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