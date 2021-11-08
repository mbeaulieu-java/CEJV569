package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.MeasurementUnit;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MeasurementUnitObservableData extends ViewObservableData implements MeasurementUnit {

    private SimpleIntegerProperty id;
    private SimpleStringProperty unitName;

    //Constructor

    public MeasurementUnitObservableData(int id, String unitName) {
        this.id.set(id);
        this.unitName.set(unitName);
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
