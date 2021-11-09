package cejv569.medicationtracker.model.operationinterfaces;

import cejv569.medicationtracker.exceptions.MedicationAlreadyAddedException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;

import java.util.List;

public interface ConfigureMedicationOperation extends ViewOperation{
    List<Ingredient> getIngredients() throws OperationFailureException;
    List <MedicationIngredients> getMedicationIngredients(int userId) throws OperationFailureException;
    List <Medication> getMedications(int userId) throws OperationFailureException;
    List <Format> getFormats() throws OperationFailureException;
    List <MeasurementUnit> getMeasurementUnits() throws OperationFailureException;
    void postMedicationIngredients(List <MedicationIngredients>medicationIngredients)
            throws OperationFailureException;
    void deleteMedicationIngredients(List <MedicationIngredients> medicationIngredients)
            throws OperationFailureException;
    void postMedication(Medication medication) throws OperationFailureException,
            MedicationAlreadyAddedException;
    void putMedication(Medication medication) throws OperationFailureException,
            MedicationAlreadyAddedException;
}
