package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Date;

public interface UserMedication extends Data{
    int getId();

    void setId(int id);

    int getMedicationId();

    void setMedicationId(int medicationId);

    int getUserId();

    void setUserId(int userId);

    double getNumberUnits();

    void setNumberUnits(double numberUnits);

    Date getPurchaseDate();

    void setPurchaseDate(Date purchaseDate);

    Date getExpiryDate();

    void setExpiryDate(Date expiryDate);

    boolean isAlert();

    void setAlert(boolean alert);

    boolean isCurrentlyTaking();

    void setCurrentlyTaking(boolean currentlyTaking);

    boolean isRecurrent();

    void setRecurrent(boolean recurrent);

    double getMaxDailyDose();

    void setMaxDailyDose(double maxDailyDose);
}
