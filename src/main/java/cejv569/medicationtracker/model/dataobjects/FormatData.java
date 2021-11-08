package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Format;

public class FormatData extends DBData implements Format {

    private int id;
    private String label;


    //Constructor
    public FormatData(int id, String label) {
        this.id = id;
        this.label = label;
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
    public String getLabel() {
        return label;
    }
    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
