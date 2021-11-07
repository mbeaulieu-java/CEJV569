package cejv569.medicationtracker.model.transactioninterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;

import java.util.List;

public interface MedicationTransaction extends DataTransaction{


    List<Ingredient> getIngredients() throws OperationFailureException;
    List <MedicationIngredients> getMedicationIngredients() throws OperationFailureException;
    List <Medication> getMedications() throws OperationFailureException;
    List <Format> getFormats() throws OperationFailureException;
    List <MeasurementUnit> getMeasurementUnits() throws OperationFailureException;
    void createMedicationIngredients(List <MedicationIngredients>medicationIngredients)
            throws OperationFailureException;
    void deleteMedicationIngredients(List <MedicationIngredients> medicationIngredients)
            throws OperationFailureException;
    void createMedication(Medication medication) throws OperationFailureException;
    void updateMedication (Medication medication) throws OperationFailureException;
    void deleteMedication(Medication medication) throws OperationFailureException;
}
