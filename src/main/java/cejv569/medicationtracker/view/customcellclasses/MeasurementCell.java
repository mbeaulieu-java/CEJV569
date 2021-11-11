package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.Format;
import cejv569.medicationtracker.model.datainterfaces.MeasurementUnit;
import javafx.scene.control.ListCell;

public class MeasurementCell extends ListCell<MeasurementUnit> {

    @Override
    protected void updateItem(MeasurementUnit item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getUnitName());
        }
    }
}
