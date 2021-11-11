package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.Medication;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ConfigureMedicationObservableData extends ViewObservableData implements Medication {

    private final static String ID_PROP_NAME = "id";
    private SimpleIntegerProperty id;

    private final static String FORMATID_PROP_NAME = "formatId";
    private SimpleIntegerProperty formatId;

    private final static String MEASUREMENTID_PROP_NAME = "measurementId";
    private SimpleIntegerProperty measurementId;

    private final static String USERID_PROP_NAME = "userId";
    private SimpleIntegerProperty userId;


    private final static String NAME_PROP_NAME = "name";
    private SimpleStringProperty name;

    //Constructor


    public ConfigureMedicationObservableData(int id, int formatId, int measurementId, int userId, String name) {

        this.id = new SimpleIntegerProperty(this,ID_PROP_NAME,id);
        this.formatId = new SimpleIntegerProperty(this,FORMATID_PROP_NAME,formatId);
        this.measurementId = new SimpleIntegerProperty(this,MEASUREMENTID_PROP_NAME,measurementId);
        this.userId = new SimpleIntegerProperty(this,USERID_PROP_NAME,userId);
        this.name = new SimpleStringProperty(this, NAME_PROP_NAME, name);
    }

    //Getters and Setters
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
    public int getFormatId() {
        return formatId.get();
    }

    public SimpleIntegerProperty formatIdProperty() {
        return formatId;
    }
    @Override
    public void setFormatId(int formatId) {
        this.formatId.set(formatId);
    }

    @Override
    public int getMeasurementId() {
        return measurementId.get();
    }

    public SimpleIntegerProperty measurementIdProperty() {
        return measurementId;
    }
    @Override
    public void setMeasurementId(int measurementId) {
        this.measurementId.set(measurementId);
    }

    @Override
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId.set(userId);
    }
}
