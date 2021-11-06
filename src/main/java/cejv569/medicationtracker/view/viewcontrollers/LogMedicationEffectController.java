package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.LogEffectOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;


public class LogMedicationEffectController extends ViewController {

    //Controls
    @FXML
    private TitledPane logEffectTitledPane;

    @FXML
    private AnchorPane logEffectAnchorPane;

    @FXML
    private TableView<?> EffectTableView;

    @FXML
    private TableColumn<?, ?> purchaseDateTableColumn;

    @FXML
    private TableColumn<?, ?> brandTableColumn;

    @FXML
    private TableColumn<?, ?> genericTableColumn;

    @FXML
    private ComboBox<?> selectEffectComboBox;

    @FXML
    private ComboBox<?> selectSeverityComboBox;

    @FXML
    private TextField logDurationTextField;

    @FXML
    private DatePicker dateNoticedDatePicker;

    @FXML
    private Button addEffectLogButton;

    @FXML
    private Button editEffectLogButton;

    @FXML
    private Button saveEffectLogButton;

    @FXML
    private Button deleteEffectLogButton;


    //Attributes
    private LogEffectOperation logMedicationOperation;
    //Setters & Getters

    public LogEffectOperation getOperation() {
        return this.logMedicationOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.logMedicationOperation = (LogEffectOperation)operation;
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }
}
