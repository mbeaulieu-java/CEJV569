package cejv569.medicationtracker.model.dataobjects;


import java.sql.Date;

public class UsersMedicationData extends DBData {

    private int id;
    private int medicationId;
    private int userId;
    private double numberUnits;
    private Date purchaseDate;
    private Date expiryDate;
    private boolean alert;
    private boolean currentlyTaking;
    private boolean recurrent;
    private double maxDailyDose;

    //Constructor

    public UsersMedicationData(int id, int medicationId, int userId, double numberUnits,
                               Date purchaseDate, Date expiryDate, boolean alert,
                               boolean currentlyTaking, boolean recurrent,
                               double maxDailyDose)
    {
        this.id = id;
        this.medicationId = medicationId;
        this.userId = userId;
        this.numberUnits = numberUnits;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.alert = alert;
        this.currentlyTaking = currentlyTaking;
        this.recurrent = recurrent;
        this.maxDailyDose = maxDailyDose;
    }

    //Setters and Getters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getNumberUnits() {
        return numberUnits;
    }

    public void setNumberUnits(double numberUnits) {
        this.numberUnits = numberUnits;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public boolean isCurrentlyTaking() {
        return currentlyTaking;
    }

    public void setCurrentlyTaking(boolean currentlyTaking) {
        this.currentlyTaking = currentlyTaking;
    }

    public boolean isRecurrent() {
        return recurrent;
    }

    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }

    public double getMaxDailyDose() {
        return maxDailyDose;
    }

    public void setMaxDailyDose(double maxDailyDose) {
        this.maxDailyDose = maxDailyDose;
    }
}
