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
import cejv569.medicationtracker.view.viewdata.CheckedListViewCheckObserver;
import cejv569.medicationtracker.view.viewdata.ConfigureMedicationObservableData;
import cejv569.medicationtracker.view.viewdata.FormatObservableData;
import cejv569.medicationtracker.view.viewdata.MedicationIngredientsObservableData;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.*;


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
    private ListChangeListener deleteIngredientListener;
    private StringConverter<Ingredient> converter;
    private CheckedListViewCheckObserver<Ingredient> checkedListCellobserver;
    private ChangeListener<? super Pair<Ingredient,Boolean>> checkedChangeListener;


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
        selectedIngredients = new ArrayList<>();

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);
        initializeControls();
        initializeEditableControls();
    }

    private void initializeEditableControls() {

        editableControlList = new ArrayList<>();
        editableControlList.add(medicationNameTextField);
        editableControlList.add(formatListView);
        editableControlList.add(measurementListView);
        editableControlList.add(ingredientsListView);
        editableControlList.add(medicationIngredientsListView);

        try {
            GUIUtility.makeEditable(editableControlList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        medicationNameTextField.addEventHandler(KeyEvent.KEY_PRESSED,e -> {
            GUIUtility.undoDisplayFieldError(medicationNameTextField,messageLabel);
        });

    }


    public void initializeUserData(int userId) {
        this.userId = userId;
        initializeMedicationValues();
        initializeMedicationIngredientsValues();
        initializeIngredientValues();
        initializeFormatValues();
        initializeMeasurementUnitValues();
        initializeListeners();
    }

    private void initializeControls(){

        saveMedicationButton.addEventHandler(ActionEvent.ACTION,e->{
            validateValues();
            if (isSavingProperty()) {
                save();}
        });

        editButton.addEventHandler(ActionEvent.ACTION,e->{setEditing();});
        addMedicationButton.addEventHandler(ActionEvent.ACTION,e->{setAdding();});

        medicationListView.setOnMouseClicked(e->{
            Medication item = medicationListView.getSelectionModel().getSelectedItem();
            int medKey = item.getId();
            int formatKey = item.getFormatId();
            int measurementKey = item.getMeasurementId();

            medicationNameTextField.textProperty().bind(
                    ((ConfigureMedicationObservableData)medicationListView
                            .getSelectionModel()
                            .getSelectedItem())
                            .nameProperty());

            for (Format f : formatListView.getItems()) {
                if (f.getId() == formatKey) {
                    formatListView
                            .getSelectionModel()
                            .select(
                                    formatListView
                                    .getItems()
                                    .indexOf(f));

                    formatListView.scrollTo(
                            formatListView
                            .getItems()
                            .indexOf(f));
                    break;
                }
            }

            for (MeasurementUnit m : measurementListView.getItems()) {
                if (m.getId() == measurementKey) {
                    measurementListView
                            .getSelectionModel()
                            .select(measurementListView
                                    .getItems()
                                    .indexOf(m));
                    measurementListView.scrollTo(
                            measurementListView
                                    .getItems()
                                    .indexOf(m));
                    break;
                }
            }

            //FILTER MED LIST

        });
    }


    private void initializeListeners() {
        medicationIngredientsListView.refresh();

        deleteIngredientListener = new ListChangeListener<MedicationIngredients>() {
            @Override
            public void onChanged(Change<? extends MedicationIngredients> c) {
                while (c.next()) {
                    if (c.wasRemoved()) {
                        List<? extends MedicationIngredients> removed = c.getRemoved();
                        for (MedicationIngredients i : removed) {
                            doMedicationIngredientDelete(i);
                        }
                    }
                }
            }
        };
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
        List<Medication> observervableDataList = new ArrayList<>();
        try {
            medicationsList = getOperation().getMedications(getUserId());
             for (Medication m: medicationsList) {
                 observervableDataList.add( new ConfigureMedicationObservableData(
                         m.getId(),
                         m.getFormatId(),
                         m.getMeasurementId(),
                         m.getUserId(),
                         m.getName()));
             }
            //medicationOList = FXCollections.observableList(medicationsList);
            medicationOList = FXCollections.observableList(observervableDataList);
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
        List<Format> formatObservableDataList = new ArrayList<>();
        try {
            formatList = getOperation().getFormats();
            if (formatList == null) {
                LogError.logUnrecoverableError(new OperationFailureException("No Formats List was obtained"));
            } else {
                for (Format f: formatList) {
                    formatObservableDataList.add(new FormatObservableData(
                            f.getId(), f.getLabel()));
                }
                //formatOList = FXCollections.observableList(formatList);
                formatOList = FXCollections.observableList(formatObservableDataList);
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
                setIngredientListParameters();
            }
        }catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    private void setIngredientListParameters(){

        ingredientsListView.setItems(ingredientOList);
        ingredientsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            converter = new StringConverter<Ingredient>() {
            @Override
            public String toString(Ingredient object) {
                return object.getName();
            }

            @Override
            public Ingredient fromString(String string) {
                ObservableList<Ingredient> list = ingredientsListView.getItems();
                Ingredient matched = null;
                for (int i = 0; i < list.size();i++) {
                    if (list.get(i).getName().equals(string)) {
                        matched = list.get(i);
                        break;
                    }
                }
                return matched;
            }
        };

        checkedListCellobserver = new CheckedListViewCheckObserver<Ingredient>();

         checkedChangeListener = (obs, old, curr) -> {

            if(curr.getValue()) {
                selectedIngredients.add(curr.getKey());
            } else {
                selectedIngredients.remove(curr.getKey());
            }
        };

        checkedListCellobserver.addListener(checkedChangeListener);

        ingredientsListView.setCellFactory(CheckBoxListCell.forListView(checkedListCellobserver::getObserverForObject,converter));
    }


    private void setAdding() {
        addingProperty.set(true);
        medicationIngredientsListView.setEditable(false);
    }

    private void setEditing() {
        setEditingProperty(true);
        try {
            GUIUtility.makeEditable(editableControlList);
            medicationIngredientsListView.getItems().addListener(deleteIngredientListener);
        } catch (Exception e) {
            LogError.logRecoverableError(e);
        }
    }

    private void save() {
        Ingredient key;
        int index = 0;
        SimpleObjectProperty<Pair<Ingredient,Boolean>> temp2;

        if (this.isAddingProperty()) {
            if (doAdd()) {
                for (Control c :editableControlList) {
                    if (c instanceof TextField){
                        ((TextField)c).setText("");
                    }
                    if(c instanceof ListView) {
                        ((ListView)c).getSelectionModel().clearSelection();
                    }
                }
                    this.initializeIngredientValues();
            }
        }
        if (this.isEditingProperty()) {
            if (doEdit()) {
                medicationIngredientsListView.getItems().removeListener(deleteIngredientListener);
            }
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
        int addMedKey = 0;
        boolean successful = false;
        List<MedicationIngredients> newMedIngredients;
        try {

            medicationData = new ConfigureMedicationObservableData(
                    0,
                    formatListView.getSelectionModel().getSelectedItem().getId(),
                    measurementListView.getSelectionModel().getSelectedItem().getId(),
                    getUserId(),
                    medicationNameTextField.getText().trim());

            addMedKey = getOperation().postMedication(medicationData);

            if (addMedKey == 0) {throw new OperationFailureException(("An error during the " +
                    "process of adding the new medication."));}

            medicationData.setId(addMedKey);

            for (int x = 0; x < medicationListView.getItems().size();x++){
               if (medicationData.getName().compareToIgnoreCase(medicationListView.getItems().get(x).getName()) < 0) {
                   medicationListView.getItems().add(x,medicationData);
                   break;
               }
            }

            if (selectedIngredients.size() != 0) {
                List<MedicationIngredients> ingredients = new ArrayList<>();
                for (Ingredient i : selectedIngredients) {
                    MedicationIngredients ing = new MedicationIngredientsObservableData(
                            0,addMedKey,i.getId(),i.getName());
                    ingredients.add(ing);
                }
                newMedIngredients = getOperation().postMedicationIngredients(ingredients);
                if (newMedIngredients != null && !newMedIngredients.isEmpty()) {
                    medicationIngredientsListView.getItems().addAll(newMedIngredients);
                    selectedIngredients.clear();
                    newMedIngredients.clear();
                }
            }
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
