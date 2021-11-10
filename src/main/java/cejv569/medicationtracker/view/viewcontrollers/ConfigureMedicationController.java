package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.*;
import cejv569.medicationtracker.model.operationinterfaces.ConfigureMedicationOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.view.customcellclasses.IngredientCell;
import cejv569.medicationtracker.view.customcellclasses.MedicationIngredientCell;
import cejv569.medicationtracker.view.viewdata.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class ConfigureMedicationController extends ViewController {
    //Controls

    @FXML
    private TitledPane configMedTitlePane;

    @FXML
    private AnchorPane configureMedAnchorPane;

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private ComboBox<String> genericComboBox;

    @FXML
    private ChoiceBox<String> formatChoiceBox;

    @FXML
    private ChoiceBox<String> measurementChoiceBox;

    @FXML
    private ListView<Map.Entry<Integer,String>> medIngredientsListView;

    @FXML
    private Button addIngredientButton;

    @FXML
    private Button removeIngredientButton;

    @FXML
    private ListView<Map.Entry<Integer,String>> ingredientsListView;

    @FXML
    private Button addMedicationButton;

    @FXML
    private Button saveMedicationButton;

    @FXML
    private Label messageLabel;

    //attributes
    private ConfigureMedicationOperation configureMedicationOperation;
    private List<Medication> medicationsList;
    private List<MedicationIngredients> medicationIngredientList;
    private List<Format> formatList;
    private List<MeasurementUnit> measurementUnitsList;
    private List<Ingredient> ingredientList;

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
        try {
           medicationIngredientList = getOperation().getMedicationIngredients(getUserId());
            medicationsList = getOperation().getMedications(getUserId());
        } catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }


    //Methods/Functions

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);
        initializeFieldValues();
        initializeButtons();

    }
    private void initializeFieldValues() {
          initializeIngredientValues();
          initializeFormatValues();
          initializeMeasurementUnitValues();
//        initializeMedicationValues();
          initializeMedicationIngredientsValues();

    }
    private void initializeButtons(){
        addIngredientButton
                .addEventHandler(ActionEvent.ACTION,e->{addRemoveIngredients();});
        removeIngredientButton
                .addEventHandler(ActionEvent.ACTION,e->{addRemoveIngredients();});
    }

    private void addRemoveIngredients (){
        if (medIngredientsListView.getItems().isEmpty()) {
            medIngredientsListView.getItems()
                    .setAll(IngredientCell.getselectedIngredients().entrySet());
        } else {
            for (Map.Entry<Integer,String> e : medIngredientsListView.getSelectionModel().getSelectedItems()) {
                IngredientCell.getselectedIngredients().remove(e.getKey());
            }
            medIngredientsListView.getItems().clear();
            medIngredientsListView
                    .getItems().setAll(IngredientCell.getselectedIngredients().entrySet());
        }

    }

    private void initializeMedicationValues () {
        List<Medication> medicationList = null;
        Stream<Medication> medicationStream = null;
        try {

        }catch (Exception e) {
            LogError.logUnrecoverableError(new OperationFailureException(e.getMessage()));
        }
    }

    private void initializeMedicationIngredientsValues() {

        try {
            medIngredientsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            medIngredientsListView.setCellFactory(ingList-> new MedicationIngredientCell());
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

        ObservableMap<Integer,String> formatMap = null;

        try {
            formatList = getOperation().getFormats();
            if (formatList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Formats List was obtained"));
            } else {

                formatMap = FXCollections.observableHashMap();
                for (Format f : formatList) {
                    formatMap.put(f.getId(), f.getLabel());
                }
            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
        if (!formatMap.isEmpty()) {
            formatChoiceBox.getItems().addAll(formatMap.values());
            formatChoiceBox.getSelectionModel().selectFirst();
        }
    }

    private void initializeMeasurementUnitValues() {


        ObservableMap<Integer,String> measurementUnitMap = null;

        try {
            measurementUnitsList = getOperation().getMeasurementUnits();
            if (measurementUnitsList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Measurement Unit List was obtained"));
            } else {

                measurementUnitMap = FXCollections.observableHashMap();
                for (MeasurementUnit mu : measurementUnitsList) {
                    measurementUnitMap.put(mu.getId(), mu.getUnitName());
                }
            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }

        if(!measurementUnitMap.isEmpty()) {
            measurementChoiceBox.getItems().addAll(measurementUnitMap.values());
            measurementChoiceBox.getSelectionModel().selectFirst();
        }
    }

    private void initializeIngredientValues() {

        ObservableMap<Integer,String> ingredientMap = null;
        ListProperty<ObservableList<Map.Entry<Integer,String>>> ingPropertyList = null;

        try {

            ingredientList = getOperation().getIngredients();
            if (ingredientList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Ingredient List was obtained"));
            } else {

                ingredientMap = FXCollections.observableHashMap();
                for ( Ingredient i : ingredientList) {
                    ingredientMap.put(i.getId(),i.getName());
                }

            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
        if (!ingredientMap.isEmpty()) {
            ingredientsListView.getItems().addAll(ingredientMap.entrySet());
            ingredientsListView.setCellFactory(ingList-> new IngredientCell());
            ingPropertyList = new SimpleListProperty<>();
        }

    }

}
