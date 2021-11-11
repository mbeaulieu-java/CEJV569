package cejv569.medicationtracker.view.customcellclasses;

import cejv569.medicationtracker.model.datainterfaces.MedicationIngredients;
import cejv569.medicationtracker.utility.GUIUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Map;

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
        this.setPadding(new Insets(5,5,5,0));
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(0,10,0,0));
        hbox.getChildren().addAll(deleteButton);

    }
    private void setButtonProperties() {
        deleteImageView.setImage(new Image(getClass()
                .getResource(IMAGE_PATH)
                .toExternalForm()));
        deleteImageView.setFitWidth(18);
        deleteImageView.setFitHeight(18);
        deleteButton.setGraphic(deleteImageView);
        deleteButton.setCursor(Cursor.OPEN_HAND);
        deleteButton.setOnAction(e->onClickItemDelete(e));
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
            System.out.println(this.getItem().getName());
        }

}
