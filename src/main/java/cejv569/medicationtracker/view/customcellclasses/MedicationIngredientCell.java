package cejv569.medicationtracker.view.customcellclasses;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

import java.util.Map;

public class MedicationIngredientCell extends ListCell<Map.Entry<Integer,String>>{

    public MedicationIngredientCell() {
        this.setPadding(new Insets(2,2,2,2));
    }


        @Override
        protected void updateItem(Map.Entry<Integer,String> ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if(empty || ingredient == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(ingredient.getValue());
                setGraphic(null);
            }
        }

}
