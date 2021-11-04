package cejv569.medicationtracker.model.datainterfaces;

public interface Effect {
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
