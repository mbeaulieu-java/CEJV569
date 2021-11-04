package cejv569.medicationtracker.model.dataobjects;


import cejv569.medicationtracker.model.datainterfaces.UserMedication;

import java.sql.Date;

public class UserMedicationData extends DBData implements UserMedication {

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

    public UserMedicationData(int id, int medicationId, int userId, double numberUnits,
                              Date purchaseDate, Date expiryDate, boolean alert,
                              boolean currentlyTaking, boolean recurrent,
                              double maxDailyDose) {
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

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getMedicationId() {
        return medicationId;
    }
    @Override
    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }
    @Override
    public int getUserId() {
        return userId;
    }
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public double getNumberUnits() {
        return numberUnits;
    }
    @Override
    public void setNumberUnits(double numberUnits) {
        this.numberUnits = numberUnits;
    }
    @Override
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    @Override
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    @Override
    public Date getExpiryDate() {
        return expiryDate;
    }
    @Override
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    @Override
    public boolean isAlert() {
        return alert;
    }
    @Override
    public void setAlert(boolean alert) {
        this.alert = alert;
    }
    @Override
    public boolean isCurrentlyTaking() {
        return currentlyTaking;
    }
    @Override
    public void setCurrentlyTaking(boolean currentlyTaking) {
        this.currentlyTaking = currentlyTaking;
    }
    @Override
    public boolean isRecurrent() {
        return recurrent;
    }
    @Override
    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }
    @Override
    public double getMaxDailyDose() {
        return maxDailyDose;
    }
    @Override
    public void setMaxDailyDose(double maxDailyDose) {
        this.maxDailyDose = maxDailyDose;
    }
}
