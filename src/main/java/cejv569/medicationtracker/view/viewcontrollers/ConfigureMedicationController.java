package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.view.viewdata.*;
import javafx.beans.property.SimpleStringProperty;
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
    private List<ConfigureMedicationObservableData> medicationsList;
    private List<MedicationIngredientsObservableData> medicationIngredientsList;
    private List<FormatObservableData> formatsList;
    private List<MeasurementUnitObservableData> measurementUnitsList;

    private List<IngredientObservableData> ingredientODList;
    private ObservableList<SimpleStringProperty> ingredientNames;
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

    protected List<ConfigureMedicationObservableData> getMedicationsList() {
        return medicationsList;
    }

    protected void setMedicationsList(List<ConfigureMedicationObservableData> medicationsList) {
        this.medicationsList = medicationsList;
    }

    protected List<MedicationIngredientsObservableData> getMedicationIngredientsList() {
        return medicationIngredientsList;
    }

    protected void setMedicationIngredientsList(List<MedicationIngredientsObservableData> medicationIngredientsList) {
        this.medicationIngredientsList = medicationIngredientsList;
    }

    protected List<FormatObservableData> getFormatsList() {
        return formatsList;
    }

    protected void setFormatsList(List<FormatObservableData> formatsList) {
        this.formatsList = formatsList;
    }

    protected List<MeasurementUnitObservableData> getMeasurementUnitsList() {
        return measurementUnitsList;
    }

    protected void setMeasurementUnitsList(List<MeasurementUnitObservableData> measurementUnitsList) {
        this.measurementUnitsList = measurementUnitsList;
    }



    //Methods/Functions

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);
        initializeFieldValues();

    }
    private void initializeFieldValues() {
        initializeIngredientValues();
        bindIngredientNameProperties();
//        initializeFormatValues();
//        initializeMeasurementUnitValues();
//        initializeMedicationValues();
//        initializeMedicationIngredientsValues();

    }

    private void initializeMedicationValues () {
        List<Medication> medicationList = null;
        Stream<Medication> medicationStream = null;
        try {
            medicationList = getOperation().getMedications(getUserId());
            if (medicationList == null) {
                LogError.logUnrecoverableError(
                        new OperationFailureException("No Medication List was obtained for user " +
                                "with ID:" + getUserId()));
            } else {

                medicationsList =  new ArrayList<ConfigureMedicationObservableData>();
                medicationStream = medicationList.stream();
                medicationStream.forEach(m->{
                    medicationsList.add(
                            new ConfigureMedicationObservableData (
                                    m.getId(),
                                    m.getFormatId(),
                                    m.getMeasurementId(),
                                    m.getUserId(),
                                    m.getBrandName(),
                                    m.getGenericName()));});
            }

        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void initializeMedicationIngredientsValues() {
        List<MedicationIngredients> medicationIngredientList = null;
        Stream<MedicationIngredients> medicationIngredientStream = null;
        try {
            medicationIngredientList = getOperation().getMedicationIngredients(getUserId());

            if (medicationIngredientList != null) {

                medicationIngredientsList =  new ArrayList<MedicationIngredientsObservableData>();
                medicationIngredientStream = medicationIngredientList.stream();
                medicationIngredientStream.forEach(m->{
                    medicationIngredientsList.add(
                            new MedicationIngredientsObservableData(
                                    m.getId(),
                                    m.getMedicationId(),
                                    m.getIngredientId(),
                                    m.getName()));});
            }

        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void initializeFormatValues() {
        List<Format> formatList = null;
        Stream<Format> formatsStream = null;
        try {
            formatList = getOperation().getFormats();
            if (formatList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Formats List was obtained"));
            } else {

                formatsList =  new ArrayList<FormatObservableData>();
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

        List<MeasurementUnit> measurementUnitList = null;
        Stream<MeasurementUnit> measurementUnitStream = null;
        try {
            measurementUnitList = getOperation().getMeasurementUnits();
            if (measurementUnitList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Measurement Unit List was obtained"));
            } else {

                measurementUnitsList =  new ArrayList<MeasurementUnitObservableData>();
                measurementUnitStream = measurementUnitList.stream();
                measurementUnitStream.forEach(mu->{
                    measurementUnitsList.add(
                            new MeasurementUnitObservableData(
                                    mu.getId(),
                                    mu.getUnitName()));});
            }

        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void initializeIngredientValues() {

        List<Ingredient> ingredientList = null;

        try {

            ingredientList = getOperation().getIngredients();
            if (ingredientList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Ingredient List was obtained"));
            } else {

                ingredientODList = new ArrayList<IngredientObservableData>();
                for ( Ingredient i : ingredientList) {
                    ingredientODList.add(
                            new IngredientObservableData(
                                    i.getId(),
                                    i.getName(),
                                    i.getMedicinal()));
                }

            }

        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void bindIngredientNameProperties () {
        SimpleStringProperty temp;
        if (ingredientODList != null && ingredientODList.size() > 0) {
            ingredientNames = FXCollections.observableArrayList();
            for (IngredientObservableData iod : ingredientODList) {
               temp = new SimpleStringProperty();
               temp.bindBidirectional(iod.nameProperty());
                ingredientNames.add(temp);
            }
            ingredientsListView = null;
            ingredientsListView = new ListView<SimpleStringProperty>(ingredientNames);
        }
    }
}
