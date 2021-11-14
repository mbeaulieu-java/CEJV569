package cejv569.medicationtracker.model.operationobjects;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.MedicationAlreadyAddedException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationTransaction;

import java.util.List;

public class MedicationOperation extends Operation implements ConfigureMedicationOperation {

    private MedicationTransaction medicationTransaction;
    //constructor
    public MedicationOperation() {
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
    public List<MedicationIngredients> getMedicationIngredients(Medication medicationParameters) throws OperationFailureException {

        List <MedicationIngredients> medicationIngredients = null;
        try {
           if (medicationParameters.getUserId() == 0) { throw new
                   OperationFailureException("No user id was provided");}

            medicationIngredients  = getTransaction().getMedicationIngredients(medicationParameters);
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
    public List<MedicationIngredients> postMedicationIngredients(List<MedicationIngredients> medicationIngredients) throws OperationFailureException {
        List<MedicationIngredients> list = null;
        try {
            if (!medicationIngredients.isEmpty()) {
                list = getTransaction().createMedicationIngredients(medicationIngredients);
                if (list == null) {
                    throw new OperationFailureException("A null medication ingredients list was returned " +
                            " when adding new medication ingredients.");
                }
            }
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
        return list;
    }

    @Override
    public void deleteMedicationIngredients(MedicationIngredients medicationIngredient) throws OperationFailureException {
        try {
            if (medicationIngredient != null) {
                getTransaction().deleteMedicationIngredients(medicationIngredient);
            }
        } catch (Exception e) {
            throw new OperationFailureException(e.getMessage());
        }
    }

    @Override
    public int postMedication(Medication medication) throws OperationFailureException, MedicationAlreadyAddedException {
        int addedMedKey = 0;
        if (medication!= null) {
            if(getTransaction().medicationAlreadyExists(medication)) {
                throw new MedicationAlreadyAddedException("A medication with this name, format" +
                        "measurement unit and for this user had already been added in the database.");
            } else {
               addedMedKey =  getTransaction().createMedication(medication);
            }
        }
        return addedMedKey;
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
