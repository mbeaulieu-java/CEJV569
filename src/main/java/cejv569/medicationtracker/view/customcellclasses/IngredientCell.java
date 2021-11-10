package cejv569.medicationtracker.view.customcellclasses;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class IngredientCell extends ListCell<Map.Entry<Integer,String>> {
    CheckBox ingredientSelection;
    public IngredientCell() {
        ingredientSelection = new CheckBox();
        ingredientSelection.setPadding(new Insets(2,0,2,0));
        this.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
        this.onMouseClickedProperty().bindBidirectional(ingredientSelection.onMouseClickedProperty());
        this.setOnMouseClicked(e->{
            mouseClicked(e);});
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
        //selected = ingredientSelection.isSelected();
        super.updateSelected(selected);
    }

    @Override
    protected boolean isItemChanged(Map.Entry<Integer, String> oldItem, Map.Entry<Integer, String> newItem) {
        return super.isItemChanged(oldItem, newItem);
    }

    @Override
    public void updateIndex(int i) {
        super.updateIndex(i);
    }

    private void mouseClicked(MouseEvent e) {
//        if(ingredientSelection.isSelected()) {
//            System.out.println("Checked");
//            this.setPressed(true);
//        } else {
//            System.out.println("Unchecked");
//            this.setPressed(false);
//        }
        System.out.println(isSelected());
        //ingredientSelection.setSelected(isSelected());
    }


}
