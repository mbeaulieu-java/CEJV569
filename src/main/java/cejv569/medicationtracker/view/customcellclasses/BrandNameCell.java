package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.Medication;
import javafx.scene.control.ListCell;

import java.util.Map;

public class BrandNameCell extends ListCell<Medication> {

    public BrandNameCell() {}

    @Override
    protected void updateItem(Medication item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getBrandName());
        }
    }
}
