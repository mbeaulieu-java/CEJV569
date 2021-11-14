package cejv569.medicationtracker.model.datainterfaces;

/**
 * The Ingredient interface is a child of the Data interface base class.  It reflects the
 * structure of the ingredients table in the database.  The interface is used to facilitate the
 * exchange of data between the ConfigureMedicationController(view layer),
 * the MedicationOperation(model layer))
 * and the MedicationTransactions controller (data layer).
 */

public interface Ingredient extends Data{

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    boolean getMedicinal();

    void setMedicinal(boolean medicinal);
}
