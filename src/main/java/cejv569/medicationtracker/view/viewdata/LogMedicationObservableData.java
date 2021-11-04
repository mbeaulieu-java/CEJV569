package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.MedicationHistory;

import java.sql.Timestamp;

public class LogMedicationObservableData extends ViewObservableData implements MedicationHistory {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getUserMedicationId() {
        return 0;
    }

    @Override
    public void setUserMedicationId(int userMedicationId) {

    }

    @Override
    public Timestamp getDatetimeTaken() {
        return null;
    }

    @Override
    public void setDatetimeTaken(Timestamp datetimeTaken) {

    }

    @Override
    public double getNumberDoses() {
        return 0;
    }

    @Override
    public void setNumberDoses(double numberDoses) {

    }
}
