package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.Format;
import cejv569.medicationtracker.model.datainterfaces.Medication;
import cejv569.medicationtracker.model.datainterfaces.MedicationIngredients;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.view.viewdata.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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
    private ChoiceBox<?> formatChoiceBox;

    @FXML
    private ChoiceBox<?> measurementChoiceBox;

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

    //attributes
    private ConfigureMedicationOperation configureMedicationOperation;
    private ObservableList<ConfigureMedicationObservableData> medicationsList;
    private ObservableList<MedicationIngredientsObservableData> medicationIngredientsList;
    private ObservableList<FormatObservableData> formatsList;
    private ObservableList<MeasurementUnitObservableData> measurementUnitsList;
    private ObservableList<IngredientObservableData> ingredientsList;
    private int userId;
    //Getters and Setters

    public ConfigureMedicationOperation getOperation() {
        return this.configureMedicationOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.configureMedicationOperation = (ConfigureMedicationOperation)operation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    protected ObservableList<ConfigureMedicationObservableData> getMedicationsList() {
        return medicationsList;
    }

    protected void setMedicationsList(ObservableList<ConfigureMedicationObservableData> medicationsList) {
        this.medicationsList = medicationsList;
    }

    protected ObservableList<MedicationIngredientsObservableData> getMedicationIngredientsList() {
        return medicationIngredientsList;
    }

    protected void setMedicationIngredientsList(ObservableList<MedicationIngredientsObservableData> medicationIngredientsList) {
        this.medicationIngredientsList = medicationIngredientsList;
    }

    protected ObservableList<FormatObservableData> getFormatsList() {
        return formatsList;
    }

    protected void setFormatsList(ObservableList<FormatObservableData> formatsList) {
        this.formatsList = formatsList;
    }

    protected ObservableList<MeasurementUnitObservableData> getMeasurementUnitsList() {
        return measurementUnitsList;
    }

    protected void setMeasurementUnitsList(ObservableList<MeasurementUnitObservableData> measurementUnitsList) {
        this.measurementUnitsList = measurementUnitsList;
    }

    protected ObservableList<IngredientObservableData> getIngredientsList() {
        return ingredientsList;
    }

    protected void setIngredientsList(ObservableList<IngredientObservableData> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    //Methods/Functions

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);


    }
    private void initializeFieldValues() {
        initializeIngredientValues();
        initializeFormatValues();
        initializeMeasurementUnitValues();
        initializeMedicationValues();
        initializeMedicationIngredientsValues();
        bindProperties();
    }

    private void initializeMedicationValues () {

    }

    private void initializeMedicationIngredientsValues() {

    }

    private void initializeFormatValues() {
        List<Format> formatList = null;
        Stream<Format> formatsStream = null;
        try {
            formatList = getOperation().getFormats();
            if (formatList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Formats List was obtained"));
            } else {

                formatsList =  FXCollections.observableArrayList();
                formatsStream = formatList.stream();
                formatsStream.forEach(f->{
                        formatsList.add(
                            new FormatObservableData(
                                f.getId(),
                                    f.getLabel()));});
            }

        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void initializeMeasurementUnitValues() {

    }

    private void initializeIngredientValues() {

    }

    private void bindProperties () {

    }
}
