package cejv569.medicationtracker.model.transactioninterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;

import java.util.List;

public interface MedicationTransaction extends DataTransaction{


    List<Ingredient> getIngredients() throws OperationFailureException;
    List <MedicationIngredients> getMedicationIngredients(Medication medicationParameters) throws OperationFailureException;
    List <Medication> getMedications(int userId) throws OperationFailureException;
    List <Format> getFormats() throws OperationFailureException;
    List <MeasurementUnit> getMeasurementUnits() throws OperationFailureException;
    List<MedicationIngredients> createMedicationIngredients(List <MedicationIngredients>medicationIngredients)
            throws OperationFailureException;
    void deleteMedicationIngredients(MedicationIngredients medicationIngredients)
            throws OperationFailureException;
    int createMedication(Medication medication) throws OperationFailureException;
    void updateMedication (Medication medication) throws OperationFailureException;
    boolean medicationAlreadyExists(Medication medication)
            throws OperationFailureException;
    List<MedicationIngredients> updateMedicationIngredients(List<MedicationIngredients> medicationIngredients)
            throws OperationFailureException;
    
}
