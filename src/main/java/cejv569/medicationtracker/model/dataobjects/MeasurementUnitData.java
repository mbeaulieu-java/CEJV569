package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.MeasurementUnit;

public class MeasurementUnitData extends DBData implements MeasurementUnit {

    private int id;
    private String unitName;

    //Constructor
    public MeasurementUnitData(int id, String unitName) {
        this.id = id;
        this.unitName = unitName;
    }

    //Setters and Getters
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getUnitName() {
        return unitName;
    }
    @Override
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
