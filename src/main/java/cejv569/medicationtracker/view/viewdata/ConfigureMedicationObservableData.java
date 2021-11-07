package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.Medication;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ConfigureMedicationObservableData extends ViewObservableData implements Medication {

    private final String ID_PROP_NAME = "id";
    private SimpleIntegerProperty id;

    private final String FORMATID_PROP_NAME = "formatId";
    private SimpleIntegerProperty formatId;

    private final String MEASUREMENTID_PROP_NAME = "measurementId";
    private SimpleIntegerProperty measurementId;

    private final String BRANDNAME_PROP_NAME = "brandName";
    private SimpleStringProperty brandName;

    private final String GENERICNAME_PROP_NAME = "genericName";
    private SimpleStringProperty genericName;

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
    public String getBrandName() {
        return brandName.get();
    }

    public SimpleStringProperty brandNameProperty() {
        return brandName;
    }
    @Override
    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
    }

    @Override
    public String getGenericName() {
        return genericName.get();
    }

    public SimpleStringProperty genericNameProperty() {
        return genericName;
    }
    @Override
    public void setGenericName(String genericName) {
        this.genericName.set(genericName);
    }
}
