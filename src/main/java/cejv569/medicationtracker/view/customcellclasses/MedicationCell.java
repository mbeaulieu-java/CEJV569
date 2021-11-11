package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.Medication;
import javafx.scene.control.ListCell;

public class MedicationCell extends ListCell<Medication> {

    public MedicationCell() {}

    @Override
    protected void updateItem(Medication item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getName());
        }
    }
}
