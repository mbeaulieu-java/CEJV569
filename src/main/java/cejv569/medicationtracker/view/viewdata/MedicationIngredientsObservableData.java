package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.MedicationIngredients;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MedicationIngredientsObservableData extends ViewObservableData implements MedicationIngredients {

    private final static String ID_PROP_NAME = "id";
    private SimpleIntegerProperty id;

    private final static String MEDICATIONID_PROP_NAME = "medicationId";
    private SimpleIntegerProperty medicationId;

    private final static String INGREDIENTID_PROP_NAME = "ingredientId";
    private SimpleIntegerProperty ingredientId;

    private final static String NAME_PROP_NAME = "name";
    private SimpleStringProperty name;

    //Constructor


    public MedicationIngredientsObservableData(int id, int medicationId, int ingredientId, String name) {
        this.id.set(id);
        this.medicationId.set(medicationId);
        this.ingredientId.set(ingredientId);
        this.name.set(name);
    }

    //Setters and getters
    @Override
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public int getMedicationId() {
        return medicationId.get();
    }

    public SimpleIntegerProperty medicationIdProperty() {
        return medicationId;
    }
    @Override
    public void setMedicationId(int medicationId) {
        this.medicationId.set(medicationId);
    }

    @Override
    public int getIngredientId() {
        return ingredientId.get();
    }

    public SimpleIntegerProperty ingredientIdProperty() {
        return ingredientId;
    }
    @Override
    public void setIngredientId(int ingredientId) {
        this.ingredientId.set(ingredientId);
    }

    @Override
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name.set(name);
    }
}
