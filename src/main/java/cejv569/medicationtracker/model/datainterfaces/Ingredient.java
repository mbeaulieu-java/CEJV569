package cejv569.medicationtracker.model.datainterfaces;

public interface Ingredient extends Data{

    int getId();

    void setId(int id);

    String getCommonName();

    void setCommonName(String commonName);

    String getScientificName();

    void setScientificName(String scientificName);

    boolean isMedicinal();

    void setMedicinal(boolean medicinal);
}
