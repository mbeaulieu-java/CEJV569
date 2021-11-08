package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.database.ResultSetColumnNamesByTransactionKey;
import cejv569.medicationtracker.database.SQLPropertiesTransactionKeys;
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
        String idColName, nameColName,medicinalColName;
        List <Ingredient> ingredientsList = new ArrayList<>();

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

        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        //return null if no records are retrieved or the ingredients list
        return (ingredientsList.isEmpty() ? null : ingredientsList);
    }

    @Override
    public List<MedicationIngredients> getMedicationIngredients() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName,medicationIdColName,ingredientIdColName, nameColName;
        List <MedicationIngredients> medicationIngredientsList = new ArrayList<>();

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

        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        //returne the medication ingredients list  or null, if no records are found.
        return (medicationIngredientsList.isEmpty() ? null : medicationIngredientsList);
    }

    @Override
    public List<Medication> getMedications() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, formatIdColName,measurementIdColName,brandNameColName,genericNameColName;
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
            measurementIdColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.MEASUREMENT_ID.columnName;
            brandNameColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.BRAND_NAME.columnName;
            genericNameColName = ResultSetColumnNamesByTransactionKey
                    .Medications_Info.GENERIC_NAME.columnName;


            //excute the Select query
            resultSet = theStatement.executeQuery();

            //read all the records from the resultset into the medication data object
            //list
            while (resultSet.next()) {

                //create the medication object instance to return the data retrieved
                //from the resultset returned by the prepared statement.
                medicationsList.add(new MedicationData(
                        resultSet.getInt(idColName),
                        resultSet.getString(labelColName)));
            }

        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        //return the formats list  or null, if no records are found.
        return (formatsList.isEmpty() ? null : formatsList);
    }

    @Override
    public List<Format> getFormats() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, labelColName;
        List <Format> formatsList = new ArrayList<>();

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

        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        //return the formats list  or null, if no records are found.
        return (formatsList.isEmpty() ? null : formatsList);
    }

    @Override
    public List<MeasurementUnit> getMeasurementUnits() throws OperationFailureException {
        ResultSet resultSet;
        PreparedStatement theStatement;
        String idColName, unitNameColName;
        List <MeasurementUnit> measurementUnitsList = new ArrayList<>();

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

        } catch(OperationFailureException | SQLException e) {
            throw new OperationFailureException (e.getMessage());
        } catch (Exception e) {
            throw new OperationFailureException (e.getMessage());
        }
        //return the formats list  or null, if no records are found.
        return (measurementUnitsList.isEmpty() ? null : measurementUnitsList);
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
