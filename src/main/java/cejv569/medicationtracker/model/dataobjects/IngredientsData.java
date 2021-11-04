package cejv569.medicationtracker.model.dataobjects;

public class IngredientsData extends DBData {

    private int id;
    private String commonName;
    private String scientificName;
    private boolean medicinal;

    //Constructor
    public IngredientsData(int id, String commonName, String scientificName, boolean medicinal) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.medicinal = medicinal;
    }

    // Setters and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public boolean isMedicinal() {
        return medicinal;
    }

    public void setMedicinal(boolean medicinal) {
        this.medicinal = medicinal;
    }
}
