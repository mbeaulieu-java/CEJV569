package cejv569.medicationtracker.model.datainterfaces;

/**
 * The Format interface is a child of the Data interface base class.  It reflects the
 * structure of the formats table in the database.  The interface is used to facilitate the
 * exchange of data between the ConfigureMedicationController(view layer),
 * the MedicationOperation(model layer))
 * and the MedicationTransactions controller (data layer).
 */

public interface Format extends Data{

  int getId();

   void setId(int id);

   String getLabel();

   void setLabel(String label);

}
