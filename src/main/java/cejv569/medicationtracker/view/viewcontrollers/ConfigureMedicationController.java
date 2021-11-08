package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class ConfigureMedicationController extends ViewController {
    //Controls

    @FXML
    private TitledPane configMedTitlePane;

    @FXML
    private AnchorPane configureMedAnchorPane;

    @FXML
    private ComboBox<?> brandComboBox;

    @FXML
    private ComboBox<?> genericComboBox;

    @FXML
    private ComboBox<?> formatComboBox;

    @FXML
    private ComboBox<?> measurementComboBox;

    @FXML
    private ListView<?> medIngredientsListView;

    @FXML
    private Button addIngredientButton;

    @FXML
    private Button removeIngredientButton;

    @FXML
    private ListView<?> ingredientsListView;

    @FXML
    private Button addMedicationButton;

    @FXML
    private Button saveMedicationButton;

    @FXML
    private Label messageLabel;


    private ConfigureMedicationOperation configureMedicationOperation;
    //Getters and Setters

    public ViewOperation getOperation() {
        return this.configureMedicationOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.configureMedicationOperation = (ConfigureMedicationOperation)operation;
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }
}
