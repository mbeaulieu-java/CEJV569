package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.MedicationPurchaseOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class LogMedicationPurchaseController extends ViewController{

   //Controls
   @FXML
   private TitledPane addPurchaseTitledPane;

    @FXML
    private AnchorPane medPurchaseAnchorPane;

    @FXML
    private ComboBox<?> selectBrandComboBox;

    @FXML
    private ComboBox<?> selectGenericComboBox;

    @FXML
    private Label unitsOfMeasureLabel;

    @FXML
    private TextField numberOfUnitsTextField;

    @FXML
    private TextField maxUnitsTextField;

    @FXML
    private DatePicker purchaseDatePicker;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private CheckBox currentlyTakingCheckBox;

    @FXML
    private CheckBox recurrentCheckBox;

    @FXML
    private CheckBox alertCheckBox;

    @FXML
    private Button addMedPurchaseButton;

    @FXML
    private Button editMedPurchaseButton;

    @FXML
    private Button saveMedPurchaseButton;

    @FXML
    private Button deleteMedPurchaseButton;

    //Attributes
    private MedicationPurchaseOperation medicationPurchaseOperation;

    //Getter & Setters

    public MedicationPurchaseOperation getOperation() {
        return this.medicationPurchaseOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.medicationPurchaseOperation = (MedicationPurchaseOperation)operation;
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }
}
