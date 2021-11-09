package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.MedicationAlreadyAddedException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationTransaction;

import java.util.List;

public class MedicationDataController extends DataController implements ConfigureMedicationOperation {

    private MedicationTransaction medicationTransaction;
    //constructor
    public MedicationDataController() {
        ApplicationController.getInstance().transactionFactory(this);
    }

    //getters and setters

    public MedicationTransaction getTransaction() {
        return this.medicationTransaction;
    }

    @Override
    public void setTransaction(DataTransaction transaction) {
        super.transaction = transaction;
        this.medicationTransaction = (MedicationTransaction)transaction;
    }

    @Override
    public List<Ingredient> getIngredients() throws OperationFailureException {

        List<Ingredient> ingredients = null;
        try {
            ingredients = getTransaction().getIngredients();
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }

        return ingredients;
    }

    @Override
    public List<MedicationIngredients> getMedicationIngredients(int userId) throws OperationFailureException {

        List <MedicationIngredients> medicationIngredients = null;
        try {
           if (userId == 0) { throw new
                   OperationFailureException("No user id was provided");}

            medicationIngredients  = getTransaction().getMedicationIngredients(userId);
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return medicationIngredients;
    }

    @Override
    public List<Medication> getMedications(int userId) throws OperationFailureException {

        List <Medication> medications = null;
        try {
            if (userId == 0) { throw new
                    OperationFailureException("No user id was provided");}

            medications  = getTransaction().getMedications(userId);
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return medications;
    }

    @Override
    public List<Format> getFormats() throws OperationFailureException {

        List <Format> formats = null;
        try {
            formats = getTransaction().getFormats();
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }

        return formats;
    }

    @Override
    public List<MeasurementUnit> getMeasurementUnits() throws OperationFailureException {
        List <MeasurementUnit> measurementUnits = null;
        try {
            measurementUnits = getTransaction().getMeasurementUnits();
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }

        return measurementUnits;
    }

    @Override
    public void postMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {

        try {
            if (!medicationIngredients.isEmpty()) {
                getTransaction().createMedicationIngredients(medicationIngredients);
            }
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public void deleteMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {
        try {
            if (!medicationIngredients.isEmpty()) {
                getTransaction().deleteMedicationIngredients(medicationIngredients);
            }
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public void postMedication(Medication medication) throws OperationFailureException, MedicationAlreadyAddedException {

        if (medication!= null) {
            if(getTransaction().medicationAlreadyExists(medication)) {
                throw new MedicationAlreadyAddedException("A medication with this name, format" +
                        "measurement unit and for this user had already been added in the database.");
            } else {
                getTransaction().createMedication(medication);
            }
        }
    }

    @Override
    public void putMedication(Medication medication) throws OperationFailureException, MedicationAlreadyAddedException {

        if (medication!= null) {
            if(getTransaction().medicationAlreadyExists(medication)) {
                throw new MedicationAlreadyAddedException("A medication with this name, format" +
                        "measurement unit and for this user had already been added in the database.");
            } else {
                getTransaction().updateMedication(medication);
            }
        }
    }
}
