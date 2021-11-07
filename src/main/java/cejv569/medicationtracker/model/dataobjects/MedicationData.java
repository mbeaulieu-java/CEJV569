package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Medication;

public class MedicationData extends DBData implements Medication {

    private int id;
    private int formatId;
    private int measurementId;
    private String brandName;
    private String genericName;

    //Constructor

    public MedicationData(int id, int formatId, int measurementId, int ingredientsId, String brandName, String genericName) {
        this.id = id;
        this.formatId = formatId;
        this.measurementId = measurementId;
        this.brandName = brandName;
        this.genericName = genericName;
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
    public String getBrandName() {
        return brandName;
    }
    @Override
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    @Override
    public String getGenericName() {
        return genericName;
    }
    @Override
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }
}
