package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Ingredient;

public class IngredientData extends DBData implements Ingredient {

    private int id;
    private String commonName;
    private String scientificName;
    private boolean medicinal;

    //Constructor
    public IngredientData(int id, String commonName, String scientificName, boolean medicinal) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.medicinal = medicinal;
    }

    // Setters and Getters
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getCommonName() {
        return commonName;
    }
    @Override
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
    @Override
    public String getScientificName() {
        return scientificName;
    }
    @Override
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
    @Override
    public boolean isMedicinal() {
        return medicinal;
    }
    @Override
    public void setMedicinal(boolean medicinal) {
        this.medicinal = medicinal;
    }
}
