package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.Ingredient;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class IngredientObservableData extends ViewObservableData implements Ingredient {


    //properties
    private final static String ID_PROP_NAME = "id";
    private ReadOnlyIntegerWrapper id;

    private final static String NAME_PROP_NAME = "name";
    private SimpleStringProperty name;

    private final static String MEDICINAL_PROP_NAME = "medicinal";
    private SimpleBooleanProperty medicinal;

    @Override
    public int getId() {
        return id.get();
    }

    @Override
    public void setId(int id) {
        this.id.set(id);
    }

    public ReadOnlyIntegerWrapper idProperty() {
        return id;
    }



    @Override
    public String getName() {
        return name.get();
    }
    @Override
    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    @Override
    public boolean getMedicinal() {
        return medicinal.get();
    }

    @Override
    public void setMedicinal(boolean medicinal) {
        this.medicinal.set(medicinal);
    }

    public SimpleBooleanProperty medicinalProperty() {
        return medicinal;
    }


}
