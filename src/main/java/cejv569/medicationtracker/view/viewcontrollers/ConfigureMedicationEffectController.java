package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureEffectOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class ConfigureMedicationEffectController extends ViewController {

    //Controls
    @FXML
    private TitledPane ConfigEffectsTitlePane;

    @FXML
    private AnchorPane effectsAnchorPane;

    @FXML
    private ComboBox<?> effectComboBox;

    @FXML
    private ComboBox<?> effectTypeComboBox;

    @FXML
    private TextArea EffectDescriptionTextArea;

    @FXML
    private CheckBox negativeCheckBox;

    @FXML
    private Button addEffectButton;

    @FXML
    private Button editEffectButton;

    @FXML
    private Button saveEffectButton;

    @FXML
    private Button deleteEffectButton;


    //Attributes
    private ConfigureEffectOperation configureEffectOperation;


    //Getters and Setters

    public ConfigureEffectOperation getOperation() {
        return configureEffectOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.configureEffectOperation = (ConfigureEffectOperation) operation;
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }

}
