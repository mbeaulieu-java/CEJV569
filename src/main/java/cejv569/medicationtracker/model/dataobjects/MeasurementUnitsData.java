package cejv569.medicationtracker.model.dataobjects;

public class MeasurementUnitsData extends DBData {

    private int id;
    private String unitName;

    //Constructor
    public MeasurementUnitsData(int id, String unitName) {
        this.id = id;
        this.unitName = unitName;
    }

    //Setters and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
