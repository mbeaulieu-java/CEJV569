package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Timestamp;

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
