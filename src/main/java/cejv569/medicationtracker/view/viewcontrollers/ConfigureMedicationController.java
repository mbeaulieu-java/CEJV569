package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.view.customcellclasses.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.List;


public class ConfigureMedicationController extends ViewController {
    //Controls

    @FXML
    private TitledPane configMedTitlePane;

    @FXML
    private AnchorPane configureMedAnchorPane;

    @FXML
    private TextField medicationNameTextField;

    @FXML
    private ListView<Format> formatListView;

    @FXML
    private ListView<MeasurementUnit> measurementListView;

    @FXML
    private Button addMedicationButton;

    @FXML
    private Button editButton;

    @FXML
    private Button saveMedicationButton;

    @FXML
    private Label messageLabel;

    @FXML
    private ListView<Ingredient> ingredientsListView;

    @FXML
    private ListView<Medication> medicationListView;

    @FXML
    private ListView<MedicationIngredients> medicationIngredientsListView;

    //Lists
    private ConfigureMedicationOperation configureMedicationOperation;
    private List<Medication> medicationsList;
    private List<MedicationIngredients> medicationIngredientList;
    private List<Format> formatList;
    private List<MeasurementUnit> measurementUnitsList;
    private List<Ingredient> ingredientList;

    //Observable Lists
    private static ObservableList<Ingredient> ingredientOList;
    private static ObservableList<Medication> medicationOList;
    private static ObservableList<MedicationIngredients> medicationIngredientOList;
    private static ObservableList<Format> formatOList;
    private static ObservableList<MeasurementUnit> measurementOList;

    //Bindable Properties
    public SimpleBooleanProperty editingProperty;
    public SimpleBooleanProperty adding;
    public SimpleBooleanProperty saving;
    public SimpleBooleanProperty disableField;
    public SimpleBooleanProperty enableField;
    //Bindable field variables
    private boolean editing;
    private boolean adding;
    private boolean saving;
    private boolean disableField;
    private boolean enableField;

    //Attributes
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

    public void initializeUserData(int userId) {
        this.userId = userId;
        initializeMedicationValues();
        initializeMedicationIngredientsValues();
        initializeIngredientValues();
        initializeFormatValues();
        initializeMeasurementUnitValues();
    }

    public static ObservableList<Ingredient> getIngredientOList() {
        return ingredientOList;
    }

    public void setIngredientOList(ObservableList<Ingredient> ingredientOList) {
        this.ingredientOList = ingredientOList;
    }

    public static ObservableList<Medication> getMedicationOList() {
        return medicationOList;
    }

    public void setMedicationOList(ObservableList<Medication> medicationOList) {
        this.medicationOList = medicationOList;
    }

    public static ObservableList<MedicationIngredients> getMedicationIngredientOList() {
        return medicationIngredientOList;
    }

    public void setMedicationIngredientOList(ObservableList<MedicationIngredients> medicationIngredientOList) {
        this.medicationIngredientOList = medicationIngredientOList;
    }

    public static ObservableList<Format> getFormatOList() {
        return formatOList;
    }

    public void setFormatOList(ObservableList<Format> formatOList) {
        this.formatOList = formatOList;
    }

    public static ObservableList<MeasurementUnit> getMeasurementOList() {
        return measurementOList;
    }

    public void setMeasurementOList(ObservableList<MeasurementUnit> measurementOList) {
        this.measurementOList = measurementOList;
    }

    //Methods/Functions

    @FXML
    void initialize() {
        // Set ObservableLists to Null
        ingredientOList = null;
        medicationOList = null;
        medicationIngredientOList = null;
        formatOList = null;
        measurementOList = null;
        editingProperty = new SimpleBooleanProperty(true);
        adding = new SimpleBooleanProperty(true);
        saving = new SimpleBooleanProperty(true);
        disableField = new SimpleBooleanProperty(true);
        enableField = new SimpleBooleanProperty(true);

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

        initializeButtons();


    }

    private void initializeButtons(){

    }

    private void addRemoveIngredients (){


    }

    private void initializeMedicationValues () {

        try {
            medicationsList = getOperation().getMedications(getUserId());
            medicationOList = FXCollections.observableList(medicationsList);
            medicationListView.setItems(medicationOList);
            medicationListView.setCellFactory(medlist-> new MedicationCell());
        }catch (Exception e) {
            System.err.println(e);
            LogError.logUnrecoverableError(new OperationFailureException(e.getMessage()));
        }
    }

    private void initializeMedicationIngredientsValues() {

        try {
            medicationIngredientList = getOperation().getMedicationIngredients(getUserId());
            medicationIngredientOList = FXCollections.observableList(medicationIngredientList);
            medicationIngredientsListView.setItems(medicationIngredientOList);
            medicationIngredientsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            medicationIngredientsListView.setCellFactory(medinglist-> new MedicationIngredientCell());
        }catch (Exception e) {
            LogError.logUnrecoverableError(new OperationFailureException(e.getMessage()));
        }
    }

    private void selectChecked(Event e) {
        ListView listView = (ListView)e.getTarget();
        System.out.println(listView.getFocusModel().getFocusedIndex());

        listView.getSelectionModel().getSelectedItems().add(listView.getFocusModel().getFocusedItem());
    }

    private void initializeFormatValues() {

        try {
            formatList = getOperation().getFormats();
            if (formatList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Formats List was obtained"));
            } else {
                formatOList = FXCollections.observableList(formatList);
                formatListView.setItems(formatOList);
                formatListView.setCellFactory(formatlist-> new FormatCell());
            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void initializeMeasurementUnitValues() {

        try {

            measurementUnitsList = getOperation().getMeasurementUnits();

        if (measurementUnitsList == null) {
            LogError.logUnrecoverableError(new OperationFailureException("No Measurement Unit List was obtained"));
        } else {
                measurementOList = FXCollections.observableList(measurementUnitsList);
                measurementListView.setItems(measurementOList);
                measurementListView.setCellFactory(measurementlist-> new MeasurementCell());
            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void initializeIngredientValues() {

        try {
            ingredientList = getOperation().getIngredients();
            if (ingredientList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Ingredient List was obtained"));
            } else {
                ingredientOList = FXCollections.observableList(ingredientList);
                ingredientsListView.setItems(ingredientOList);
                ingredientsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                ingredientsListView.setCellFactory(inglist-> new IngredientCell());
            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private boolean doEdit(){

        return false;
    }

    private boolean doAdd(){

        return false;
    }

    public static boolean doMedicationIngredientDelete(MedicationIngredients medicationIngredient){
        return false;
    }



}
