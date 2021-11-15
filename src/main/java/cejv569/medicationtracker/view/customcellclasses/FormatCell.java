package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.Format;
import javafx.scene.control.ListCell;

public class FormatCell extends ListCell<Format> {

        public FormatCell() {}

        @Override
        protected void updateItem(Format item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getLabel());
            }
        }
}
