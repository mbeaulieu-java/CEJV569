package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.MeasurementUnit;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MeasurementUnitObservableData extends ViewObservableData implements MeasurementUnit {

    private final static String ID_PROP_NAME = "id";
    private SimpleIntegerProperty id;

    private final static String UNITNAME_PROP_NAME = "unitName";

    private SimpleStringProperty unitName;

    //Constructor

    public MeasurementUnitObservableData(int id, String unitName) {
        this.id = new SimpleIntegerProperty(this,ID_PROP_NAME,id);
        this.unitName = new SimpleStringProperty(this,UNITNAME_PROP_NAME,unitName);
    }

    //Setters and Getters


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
    public String getUnitName() {
        return unitName.get();
    }

    public SimpleStringProperty unitNameProperty() {
        return unitName;
    }

    @Override
    public void setUnitName(String unitName) {
        this.unitName.set(unitName);
    }
}
