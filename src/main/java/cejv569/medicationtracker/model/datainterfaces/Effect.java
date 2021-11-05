package cejv569.medicationtracker.model.datainterfaces;
/**
 * The Effect interface is a child of the Data interface base class.  It reflects the
 * structure of the Effects table in the database.  The interface is used to facilitate the
 * exchange of data between the ConfigureMedicationEffectController (view layer), the EffectDataController
 * (model layer) and the EffectTransactions controller (data layer).
 */

public interface Effect extends Data{
    int getId();

   void setId(int id);

   String getLabel();


   void setLabel(String label);

    String getEffectDescription();

    void setEffectDescription(String effectDescription);

    boolean isNegative();

    void setNegative(boolean negative);

    String getBodyLocation();

    void setBodyLocation(String bodyLocation);
}
