package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.LogMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class LogMedicationController extends ViewController{

    //Controls

    @FXML
    private TitledPane logMedicationTitledPane;

    @FXML
    private AnchorPane logMedicationAnchorPane;

    @FXML
    private ComboBox<?> brandMedTakenComboBox;

    @FXML
    private ComboBox<?> genericMedTakenComboBox;

    @FXML
    private TextField dosesTakenTextField;

    @FXML
    private DatePicker dateTakenDatePicker;

    @FXML
    private Label purchaseDateLabel;

    @FXML
    private Button addMedTakenButton;

    @FXML
    private Button editMedTakenButton;

    @FXML
    private Button saveMedTakenButton;

    @FXML
    private Button deleteMedTakenButton;

   //Attributes
   private LogMedicationOperation logMedicationOperation;

   //Getters & Setters

    public LogMedicationOperation getOperation() {
        return this.logMedicationOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.logMedicationOperation = (LogMedicationOperation)operation;
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }
}
