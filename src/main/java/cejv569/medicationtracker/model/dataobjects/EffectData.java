package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Effect;

public class EffectData extends DBData implements Effect {

    private int Id;
    private String label;
    private String effectDescription;
    private boolean negative;
    private String bodyLocation;

    //Constructor
    public EffectData(int id, String label, String effectDescription, boolean negative, String bodyLocation) {
        Id = id;
        this.label = label;
        this.effectDescription = effectDescription;
        this.negative = negative;
        this.bodyLocation = bodyLocation;
    }

    //Getters and Setters

    @Override
    public int getId() {
        return Id;
    }
    @Override
    public void setId(int id) {
        Id = id;
    }
    @Override
    public String getLabel() {
        return label;
    }
    @Override
    public void setLabel(String label) {
        this.label = label;
    }
    @Override
    public String getEffectDescription() {
        return effectDescription;
    }
    @Override
    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }
    @Override
    public boolean isNegative() {
        return negative;
    }
    @Override
    public void setNegative(boolean negative) {
        this.negative = negative;
    }
    @Override
    public String getBodyLocation() {
        return bodyLocation;
    }
    @Override
    public void setBodyLocation(String bodyLocation) {
        this.bodyLocation = bodyLocation;
    }
}
