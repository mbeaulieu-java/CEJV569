package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Medication;

public class MedicationData extends DBData implements Medication {

    private int id;
    private int formatId;
    private int measurementId;
    private int userId;
    private String name;

    //Constructor

    public MedicationData(int id, int formatId, int measurementId, int userId, String name) {
        this.id = id;
        this.formatId = formatId;
        this.measurementId = measurementId;
        this.userId = userId;
        this.name = name;

    }

    //Getters and Setters

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getFormatId() {
        return formatId;
    }
    @Override
    public void setFormatId(int formatId) {
        this.formatId = formatId;
    }
    @Override
    public int getMeasurementId() {
        return measurementId;
    }
    @Override
    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = this.name;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
