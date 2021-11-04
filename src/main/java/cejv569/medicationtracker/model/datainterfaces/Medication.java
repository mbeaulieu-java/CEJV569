package cejv569.medicationtracker.model.datainterfaces;

public interface Medication {

   int getId();

   void setId(int id);

   int getFormatId() ;

    void setFormatId(int formatId);

   int getMeasurementId();

   void setMeasurementId(int measurementId);

    int getIngredientsId();

    void setIngredientsId(int ingredientsId);

    String getBrandName();

    void setBrandName(String brandName);

    String getGenericName();

    void setGenericName(String genericName);
}
