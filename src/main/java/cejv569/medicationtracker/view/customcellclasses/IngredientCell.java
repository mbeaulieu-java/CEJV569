package cejv569.medicationtracker.view.customcellclasses;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

import java.util.Map;

public class IngredientCell extends ListCell<Map.Entry<Integer,String>> {
    CheckBox ingredientSelection;
    public IngredientCell() {
        ingredientSelection = new CheckBox();
        ingredientSelection.setPadding(new Insets(2,0,2,0));
        ingredientSelection.setSelected(false);
        this.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Map.Entry<Integer,String> ingredients, boolean empty) {
        super.updateItem(ingredients, empty);

        if(empty || ingredients == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);
            ingredientSelection.setText(ingredients.getValue());
            setGraphic(ingredientSelection);
        }
    }
}
