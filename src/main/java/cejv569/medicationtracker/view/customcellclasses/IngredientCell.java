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
import java.util.*;

public class IngredientCell extends CheckBoxListCell<Ingredient> implements Callback<Ingredient,ObservableValue<Boolean>>{

    Callback<Ingredient, ObservableValue<Boolean>> callbackProperty;
    public IngredientCell(Callback<Ingredient, ObservableValue<Boolean>> getSelectedProperty, StringConverter<Ingredient> converter) {
        super(getSelectedProperty, converter);
        callbackProperty = getSelectedProperty;

    }

    @Override
    public ObservableValue<Boolean> call(Ingredient param) {
        return  callbackProperty.call(param);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    @Override
    public void updateSelected(boolean selected) {
        selected = ((CheckBox)getStyleableNode()).isSelected();
        super.updateSelected(selected);
    }
}
