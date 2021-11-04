package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.UserMedication;

import java.sql.Date;

public class MedicationPurchaseObservableData extends ViewObservableData implements UserMedication {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getMedicationId() {
        return 0;
    }

    @Override
    public void setMedicationId(int medicationId) {

    }

    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public void setUserId(int userId) {

    }

    @Override
    public double getNumberUnits() {
        return 0;
    }

    @Override
    public void setNumberUnits(double numberUnits) {

    }

    @Override
    public Date getPurchaseDate() {
        return null;
    }

    @Override
    public void setPurchaseDate(Date purchaseDate) {

    }

    @Override
    public Date getExpiryDate() {
        return null;
    }

    @Override
    public void setExpiryDate(Date expiryDate) {

    }

    @Override
    public boolean isAlert() {
        return false;
    }

    @Override
    public void setAlert(boolean alert) {

    }

    @Override
    public boolean isCurrentlyTaking() {
        return false;
    }

    @Override
    public void setCurrentlyTaking(boolean currentlyTaking) {

    }

    @Override
    public boolean isRecurrent() {
        return false;
    }

    @Override
    public void setRecurrent(boolean recurrent) {

    }

    @Override
    public double getMaxDailyDose() {
        return 0;
    }

    @Override
    public void setMaxDailyDose(double maxDailyDose) {

    }
}
