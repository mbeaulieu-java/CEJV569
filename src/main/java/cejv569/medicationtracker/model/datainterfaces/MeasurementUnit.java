package cejv569.medicationtracker.model.datainterfaces;

/**
 * The MeasurementUnit interface is a child of the Data interface base class.  It reflects the
 * structure of the measurement_units table in the database.  The interface is used to facilitate the
 * exchange of data between the ConfigureMedicationController(view layer),
 * LogMedicationPurchaseController (view layer),
 * the MedicationDataController(model layer))
 * and the MedicationTransactions controller (data layer).
 */



public interface MeasurementUnit extends Data{
  int getId();

   void setId(int id);

   String getUnitName();

  void setUnitName(String unitName);

}
