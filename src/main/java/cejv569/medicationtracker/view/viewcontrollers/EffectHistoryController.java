package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.EffectHistoryOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class EffectHistoryController extends ViewController {

    //Controls
    @FXML
    private TitledPane medEffectsTitledPane;

    @FXML
    private AnchorPane effectHistoryAnchorPane;

    @FXML
    private TableView<?> effectHistoryTableView;

    @FXML
    private TableColumn<?, ?> effectDateTableColumn;

    @FXML
    private TableColumn<?, ?> EffectTableColumn;

    @FXML
    private TableColumn<?, ?> effectMedBrandTableColumn;

    @FXML
    private TableColumn<?, ?> effectMedGenericTableColumn;

    @FXML
    private TextArea effectHistoryTextArea;

    @FXML
    private Label effectHistoryDurationLabel;

    @FXML
    private Label effectHistorySeverityLabel;

    //Attributes
    private EffectHistoryOperation effectHistoryOperation;


    //Getters and Setters

    public EffectHistoryOperation getOperation() {
        return this.effectHistoryOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.effectHistoryOperation = (EffectHistoryOperation)operation;
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }
}
