package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Date;

/**
 * The Medication interface is a child of the Data interface base class.  It reflects the
 * structure of the user_medication table in the database.  The interface is used to facilitate the
 * exchange of data between
 * the LogMedicationPurchaseController (view layer),
 * LogMedicationController (view layer),
 * LogMedicationEffectController(view layer),
 * MedLogOperation (model layer),
 * MedPurchaseOperation(model layer)
 * and the UserMedicationTransactions controller(data layer).
 */

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
