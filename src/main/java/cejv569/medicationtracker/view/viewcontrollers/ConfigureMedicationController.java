package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.MedicationAlreadyAddedException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.customcellclasses.*;
import cejv569.medicationtracker.view.viewdata.ConfigureMedicationObservableData;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private ListView<Ingredient> ingredientsListView;

    @FXML
    private ListView<Medication> medicationListView;

    @FXML
    private ListView<MedicationIngredients> medicationIngredientsListView;

    @FXML
    private Label messageLabel;

    //Lists

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
    private SimpleBooleanProperty editingProperty;
    private SimpleBooleanProperty addingProperty;
    private SimpleBooleanProperty savingProperty;
    private SimpleBooleanProperty disableProperty;
    private SimpleBooleanProperty enableProperty;
    private static SimpleBooleanProperty deletingProperty;

//    //Bindable field variables
    private boolean editing;
    private boolean adding;
    private boolean saving;
    private boolean disableField;
    private boolean enableField;
//
    //Attributes
    private int userId;
    private static ConfigureMedicationOperation configureMedicationOperation;
    private List<Control> editableControlList;
    private static List<Ingredient> selectedIngredients;


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

    public boolean isEditingProperty() {
        return editingProperty.get();
    }

    public SimpleBooleanProperty editingPropertyProperty() {
        return editingProperty;
    }

    public void setEditingProperty(boolean editingProperty) {
        this.editingProperty.set(editingProperty);
    }

    public boolean isAddingProperty() {
        return addingProperty.get();
    }

    public SimpleBooleanProperty addingProperty() {
        return addingProperty;
    }

    public void setAddingProperty(boolean addingProperty) {
        this.addingProperty.set(addingProperty);
    }

    public boolean isSavingProperty() {
        return savingProperty.get();
    }

    public SimpleBooleanProperty savingProperty() {
        return savingProperty;
    }

    public void setSavingProperty(boolean savingProperty) {
        this.savingProperty.set(savingProperty);
    }

    public boolean isDisableProperty() {
        return disableProperty.get();
    }

    public SimpleBooleanProperty disableProperty() {
        return disableProperty;
    }

    public void setDisableProperty(boolean disableProperty) {
        this.disableProperty.set(disableProperty);
    }

    public boolean isEnableProperty() {
        return enableProperty.get();
    }

    public SimpleBooleanProperty enableProperty() {
        return enableProperty;
    }

    public void setEnableProperty(boolean enableProperty) {
        this.enableProperty.set(enableProperty);
    }

    public boolean isDeletingProperty() {
        return deletingProperty.get();
    }

    public SimpleBooleanProperty deletingProperty() {
        return deletingProperty;
    }

    public void setDeletingProperty(boolean deletingProperty) {
        this.deletingProperty.set(deletingProperty);
    }

    public static List<Ingredient> getSelectedIngredients() {
        return (selectedIngredients == null ?
                selectedIngredients = new ArrayList<>() : selectedIngredients);
    }

    public static void setSelectedIngredients(List<Ingredient> selectedIngredients) {
        ConfigureMedicationController.selectedIngredients = selectedIngredients;
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
        editingProperty = new SimpleBooleanProperty(false);
        addingProperty = new SimpleBooleanProperty(false);
        savingProperty = new SimpleBooleanProperty(false);
        disableProperty = new SimpleBooleanProperty(false);
        enableProperty = new SimpleBooleanProperty(false);
        deletingProperty = new SimpleBooleanProperty(false);

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);
        initializeButtons();
        initializeEditableControls();
    }

    private void initializeEditableControls() {

        editableControlList = new ArrayList<>();
        editableControlList.add(medicationNameTextField);
        editableControlList.add(formatListView);
        editableControlList.add(measurementListView);
        editableControlList.add(ingredientsListView);

        try {
            GUIUtility.makeEditable(editableControlList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }


    public void initializeUserData(int userId) {
        this.userId = userId;
        initializeMedicationValues();
        initializeMedicationIngredientsValues();
        initializeIngredientValues();
        initializeFormatValues();
        initializeMeasurementUnitValues();
    }

    private void initializeButtons(){

        saveMedicationButton.addEventHandler(ActionEvent.ACTION,e->{
            validateValues();
            if (isSavingProperty()) {
                Save();}
        });

        editButton.addEventHandler(ActionEvent.ACTION,e->{setEditing();});
        addMedicationButton.addEventHandler(ActionEvent.ACTION,e->{setAdding();});


    }
    private boolean validateValues() {
        boolean successful = false;

        try {
            successful = true;
            savingProperty.set(successful);
        }catch (Exception e){
            LogError.logUnrecoverableError(new OperationFailureException("Error during field validation. " + e.getMessage()));
        }
        return successful;
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

    private void setAdding() {
        addingProperty.set(true);
    }
    private void setEditing() {
        setEditingProperty(true);
    }

    private void Save() {
        if (this.isAddingProperty()) {
            doAdd();
        } else if (this.isEditingProperty()) {
            doEdit();
        } else {
            doMedicationIngredientDelete();
        }
    }

    private boolean doEdit(){

        boolean successful = false;
        try {

        } catch (Exception e) {
            LogError.logUnrecoverableError(
                    new OperationFailureException("Error occured during delete. Error message " + e.getMessage()));

        }
        return successful;
    }

    private boolean doAdd(){


        Medication medicationData = null;

        boolean successful = false;
        try {
            //int id, int formatId, int measurementId, int userId, String name)
            medicationData = new ConfigureMedicationObservableData(
                    0,
                    formatListView.getSelectionModel().getSelectedItem().getId(),
                    measurementListView.getSelectionModel().getSelectedItem().getId(),
                    getUserId(),
                    medicationNameTextField.getText().trim());

            getOperation().postMedication(medicationData);

            if ()
            successful = true;
            addingProperty.set(false);

        } catch (MedicationAlreadyAddedException e) {
            successful = false;

            GUIUtility.displayFieldError(medicationNameTextField,messageLabel,
                    UserMessages.ErrorMessages.MEDICATION_ALREADY_IN_LIST_ERROR_MESSAGE.message);
        } catch (Exception e) {
            LogError.logUnrecoverableError(
                    new OperationFailureException("Error occured during delete. Error message " + e.getMessage()));
        }
        return successful;
    }

    public static boolean doMedicationIngredientDelete(){
        boolean successful = false;
        try {

        } catch (Exception e) {
            LogError.logUnrecoverableError(
                    new OperationFailureException("Error occured during delete. Error message " + e.getMessage()));
        }
        return successful;
    }

    public static boolean doMedicationIngredientDelete(MedicationIngredients medicationIngredient){
        boolean successful = false;
        deletingProperty.set(true);
        try {
            configureMedicationOperation.deleteMedicationIngredients(medicationIngredient);
            getMedicationIngredientOList().remove(medicationIngredient);
        } catch (Exception e) {
            LogError.logUnrecoverableError(
                    new OperationFailureException("Error occured during delete. Error message " + e.getMessage()));
        }
        deletingProperty.set(false);
        return successful;
    }


}
