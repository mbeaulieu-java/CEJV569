package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.Format;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FormatObservableData extends ViewObservableData implements Format {


    private final static String ID_PROP_NAME = "id";
    private SimpleIntegerProperty id;

    private final static String LABEL_PROP_NAME = "label";
    private SimpleStringProperty label;

    //constructor

    public FormatObservableData(int id, String label) {
        this.id = new SimpleIntegerProperty(this,ID_PROP_NAME,id);
        this.label = new SimpleStringProperty(this,LABEL_PROP_NAME,label);
    }

    //Setters and Getters

    @Override
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label.set(label);
    }
}
