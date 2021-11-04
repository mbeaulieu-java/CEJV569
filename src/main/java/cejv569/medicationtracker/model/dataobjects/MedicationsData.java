package cejv569.medicationtracker.model.dataobjects;

public class MedicationsData extends DBData {

    private int id;
    private int formatId;
    private int measurementId;
    private int ingredientsId;
    private String brandName;
    private String genericName;

    //Constructor

    public MedicationsData(int id, int formatId, int measurementId, int ingredientsId, String brandName, String genericName) {
        this.id = id;
        this.formatId = formatId;
        this.measurementId = measurementId;
        this.ingredientsId = ingredientsId;
        this.brandName = brandName;
        this.genericName = genericName;
    }

    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormatId() {
        return formatId;
    }

    public void setFormatId(int formatId) {
        this.formatId = formatId;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public int getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }
}
