package cejv569.medicationtracker.model.datainterfaces;

public interface MedicationIngredients extends Data{

    int getId();
    void setId(int id);

    int getMedicationId();
    void setMedicationId(int medicationId);

    int getIngredientId();
    void setIngredientId(int ingredientId);

    String getName();
    void setName(String name);
}
