package cejv569.medicationtracker.model.dataobjects;


import cejv569.medicationtracker.model.datainterfaces.MedicationIngredients;

public class MedicationIngredientsData extends DBData implements MedicationIngredients {

    private int id;
    private int medicationId;
    private int ingredientId;
    private String name;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getMedicationId() {
        return this.medicationId;
    }

    @Override
    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    @Override
    public int getIngredientId() {
        return this.ingredientId;
    }

    @Override
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
