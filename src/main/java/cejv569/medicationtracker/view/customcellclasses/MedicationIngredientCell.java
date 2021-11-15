package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.MedicationIngredients;
import cejv569.medicationtracker.view.viewcontrollers.ConfigureMedicationController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MedicationIngredientCell extends ListCell<MedicationIngredients>{
    private static final String IMAGE_PATH = "/cejv569/medicationtracker/assets/delete.png";

    Button deleteButton;
    ImageView deleteImageView;
    HBox hbox;
    public MedicationIngredientCell() {

        deleteButton = new Button();
        deleteImageView = new ImageView();
        hbox= new HBox();
        setButtonProperties();
        this.setPadding(new Insets(1,5,1,0));
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(0,10,0,0));
        hbox.getChildren().add(deleteButton);

        deleteButton.setOnAction(e->onClickItemDelete(e));
    }

    private void setButtonProperties() {
        deleteImageView.setImage(new Image(getClass()
                .getResource(IMAGE_PATH)
                .toExternalForm()));
        deleteImageView.setFitWidth(18);
        deleteImageView.setFitHeight(18);
        deleteButton.setGraphic(deleteImageView);
        deleteButton.setCursor(Cursor.OPEN_HAND);
        deleteButton.setDefaultButton(true);
    }

        @Override
        protected void updateItem(MedicationIngredients ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if(empty || ingredient == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(hbox);
                setText(ingredient.getName());
            }
        }

        private void onClickItemDelete(ActionEvent e) {
            if (ConfigureMedicationController.editing){
                this.getListView().getItems().remove(this.getItem());
                this.getListView().refresh();
                this.getListView().requestFocus();
            }
        }

}
