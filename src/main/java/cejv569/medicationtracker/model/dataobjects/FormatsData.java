package cejv569.medicationtracker.model.dataobjects;

public class FormatsData extends DBData {

    private int id;
    private String label;
    private String formatDescription;

    //Constructor
    public FormatsData(int id, String label, String formatDescription) {
        this.id = id;
        this.label = label;
        this.formatDescription = formatDescription;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFormatDescription() {
        return formatDescription;
    }

    public void setFormatDescription(String formatDescription) {
        this.formatDescription = formatDescription;
    }
}
