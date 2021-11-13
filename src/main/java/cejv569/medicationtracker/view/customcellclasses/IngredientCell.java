package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.Ingredient;
import cejv569.medicationtracker.view.viewcontrollers.ConfigureMedicationController;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientCell extends CheckBoxListCell<Ingredient> implements Callback<Ingredient,ObservableValue<Boolean>>{


//    public IngredientCell() {
//        //ingredientSelection = new CheckBox();
//        //ingredientSelection.setPadding(new Insets(4,0,4,2));
//        //this.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
//
// this.setOnMouseClicked(this::ingredientClicked);
//        this.setOnMouseClicked(e->itemClicked(e));
//
////        ingredientSelection.setOnMouseClicked(e->{
////            ingredientClicked(e);});
//
//    }


    public IngredientCell(Callback<Ingredient, ObservableValue<Boolean>> getSelectedProperty) {
        super(getSelectedProperty);
    }

    @Override
    public ObservableValue<Boolean> call(Ingredient param) {
        return null;
    }

    @Override
    public void updateItem(Ingredient ingredients, boolean empty) {
        super.updateItem(ingredients, empty);

        if(empty || ingredients == null) {
            setText(null);
            setGraphic(null);
        } else {

            //setText("");
           // ingredientSelection.setText(ingredients.getName());
            //setGraphic(ingredientSelection);
        }
    }

    @Override
    public void updateSelected(boolean selected) {

        super.updateSelected(ingredientSelection.isSelected() || selected);

    }


    private void ingredientClicked(MouseEvent e) {

        this.updateSelected(true);
        if (ingredientSelection.isSelected()) {
            //ConfigureMedicationController.getSelectedIngredients().add(this.getItem());
        } else {
            //selectedIngredients.remove(this.getItem());
        }

    }

    private void itemClicked(MouseEvent e) {
        ingredientSelection.setSelected(!ingredientSelection.isSelected());
    }

}
