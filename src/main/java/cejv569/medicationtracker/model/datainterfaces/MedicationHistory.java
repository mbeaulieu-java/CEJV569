package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Timestamp;

/**
 * The Medication History interface is a child of the Data interface base class.  It reflects the
 * structure of the med_history table in the database.  The interface is used to facilitate the
 * exchange of data between the LogMedicationController (view layer),
 * MedLogOperation (model layer)
 * and the MedicationHistoryTransactions controller(data layer).
 */

public interface MedicationHistory extends Data{
    int getId();

    void setId(int id);

    int getUserMedicationId();

    void setUserMedicationId(int userMedicationId);

    Timestamp getDatetimeTaken();

   void setDatetimeTaken(Timestamp datetimeTaken);

   double getNumberDoses();

   void setNumberDoses(double numberDoses);
}
