package cejv569.medicationtracker.model.dataobjects;

public class EffectsData extends DBData {

    private int Id;
    private String label;
    private String effectDescription;
    private boolean negative;
    private String bodyLocation;

    //Constructor
    public EffectsData(int id, String label, String effectDescription, boolean negative, String bodyLocation) {
        Id = id;
        this.label = label;
        this.effectDescription = effectDescription;
        this.negative = negative;
        this.bodyLocation = bodyLocation;
    }

    //Getters and Setters


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public String getBodyLocation() {
        return bodyLocation;
    }

    public void setBodyLocation(String bodyLocation) {
        this.bodyLocation = bodyLocation;
    }
}
