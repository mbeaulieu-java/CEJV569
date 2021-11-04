package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.MedicationHistory;

import java.sql.Timestamp;

public class MedicationHistoryData extends DBData implements MedicationHistory {

    private int id;
    private int userMedicationId;
    private Timestamp datetimeTaken;
    private double numberDoses;

    //constructor


    public MedicationHistoryData(int id, int userMedicationId, Timestamp datetimeTaken, double numberDoses) {
        this.id = id;
        this.userMedicationId = userMedicationId;
        this.datetimeTaken = datetimeTaken;
        this.numberDoses = numberDoses;
    }

    //Getters and Setters

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getUserMedicationId() {
        return userMedicationId;
    }
    @Override
    public void setUserMedicationId(int userMedicationId) {
        this.userMedicationId = userMedicationId;
    }
    @Override
    public Timestamp getDatetimeTaken() {
        return datetimeTaken;
    }
    @Override
    public void setDatetimeTaken(Timestamp datetimeTaken) {
        this.datetimeTaken = datetimeTaken;
    }
    @Override
    public double getNumberDoses() {
        return numberDoses;
    }
    @Override
    public void setNumberDoses(double numberDoses) {
        this.numberDoses = numberDoses;
    }
}
