package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.view.viewcontrollers.ConfigureMedicationController;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientCell extends ListCell<Map.Entry<Integer,String>> {
    CheckBox ingredientSelection;
    private static Map<Integer,String> selectedIngredients = new HashMap<>();
    public IngredientCell() {
        ingredientSelection = new CheckBox();
        ingredientSelection.setPadding(new Insets(2,0,2,0));
        this.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);

        ingredientSelection.setOnMouseClicked(e->{
            ingredientClicked(e);});
        this.setOnMouseClicked(e->itemClicked(e));

    }

    public static Map<Integer,String> getselectedIngredients() {
        return selectedIngredients;
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

    @Override
    public void updateSelected(boolean selected) {

        super.updateSelected(ingredientSelection.isSelected() || selected);

    }


    private void ingredientClicked(MouseEvent e) {

        this.updateSelected(true);
        if (ingredientSelection.isSelected()) {
            selectedIngredients.put(this.getItem().getKey(),this.getItem().getValue());
        } else {
            selectedIngredients.remove(this.getItem().getKey());
        }


    }

    private void itemClicked(MouseEvent e) {
        ingredientSelection.setSelected(!ingredientSelection.isSelected());
    }

}
