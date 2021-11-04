package cejv569.medicationtracker.model.dataobjects;

import java.sql.Timestamp;

public class MedicationHistoryData extends DBData {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserMedicationId() {
        return userMedicationId;
    }

    public void setUserMedicationId(int userMedicationId) {
        this.userMedicationId = userMedicationId;
    }

    public Timestamp getDatetimeTaken() {
        return datetimeTaken;
    }

    public void setDatetimeTaken(Timestamp datetimeTaken) {
        this.datetimeTaken = datetimeTaken;
    }

    public double getNumberDoses() {
        return numberDoses;
    }

    public void setNumberDoses(double numberDoses) {
        this.numberDoses = numberDoses;
    }
}
