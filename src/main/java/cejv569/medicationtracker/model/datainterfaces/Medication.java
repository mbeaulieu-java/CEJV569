package cejv569.medicationtracker.model.datainterfaces;
/**
 * The Medication interface is a child of the Data interface base class.  It reflects the
 * structure of the medication table in the database.  The interface is used to facilitate the
 * exchange of data between the ConfigureMedicationtController (view layer),
 * the LogMedicationPurchaseController (view layer),
 * LogMedicationController (view layer),
 * LogMedicationEffectController(view layer),
 * EffectHistoryController(view layer),
 * MedicationDataController(model layer),
 * MedLogDataController (model layer),
 * MedPurchaseDataController(model layer),
 * EffectLogDataController (model layer),
 * EffectHistoryDataController (model layer),
 * and the UserMedicationTransactions controller(data layer).
 */

public interface Medication extends Data{

   int getId();

   void setId(int id);

   int getFormatId() ;

    void setFormatId(int formatId);

   int getMeasurementId();

   void setMeasurementId(int measurementId);

    String getName();

    void setName(String name);

    int getUserId();

    void setUserId(int userId);

}
