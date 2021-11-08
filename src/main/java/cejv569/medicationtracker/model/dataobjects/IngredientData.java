package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Ingredient;

public class IngredientData extends DBData implements Ingredient {

    private int id;
    private String name;
    private boolean medicinal;

    //Constructor
    public IngredientData(int id, String name, boolean medicinal) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean getMedicinal() {
        return medicinal;
    }
    @Override
    public void setMedicinal(boolean medicinal) {
        this.medicinal = medicinal;
    }
}
