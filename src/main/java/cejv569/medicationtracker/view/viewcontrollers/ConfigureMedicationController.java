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
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
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
    private SimpleBooleanProperty editProperty;
    private SimpleBooleanProperty addProperty;
    private SimpleBooleanProperty saveProperty;
    private SimpleBooleanProperty disableProperty;
    private SimpleBooleanProperty enableProperty;
    private static SimpleBooleanProperty deletingProperty;

    //Bindings
    private BooleanBinding disableSave;
    private BooleanBinding disableEdit;
    private BooleanBinding disableAdd;

//  User Activity State Flags
    private boolean editing;
    private boolean adding;
    private boolean deleting;

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

    public boolean getEditProperty() {
        return editProperty.get();
    }

    public SimpleBooleanProperty editPropertyProperty() {
        return editProperty;
    }

    public void setEditProperty(boolean editProperty) {
        this.editProperty.set(editProperty);
    }

    public boolean getAddProperty() {
        return addProperty.get();
    }

    public SimpleBooleanProperty addingProperty() {
        return addProperty;
    }

    public void setAddProperty(boolean addProperty) {
        this.addProperty.set(addProperty);
    }

    public boolean getSaveProperty() {
        return saveProperty.get();
    }

    public SimpleBooleanProperty savingProperty() {
        return saveProperty;
    }

    public void setSaveProperty(boolean saveProperty) {
        this.saveProperty.set(saveProperty);
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
        editProperty = new SimpleBooleanProperty(false);
        addProperty = new SimpleBooleanProperty(false);
        saveProperty = new SimpleBooleanProperty(false);
//        disableProperty = new SimpleBooleanProperty(false);
//        enableProperty = new SimpleBooleanProperty(false);
        deletingProperty = new SimpleBooleanProperty(false);
        selectedIngredients = new ArrayList<>();

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);
        initializeControlEventHandlers();
        initializeEditableControls();
    }

    private void initializeEditableControls() {

        editableControlList = new ArrayList<>();
        editableControlList.add(medicationNameTextField);
        editableControlList.add(formatListView);
        editableControlList.add(measurementListView);

        try {
            GUIUtility.makeUnEditable(editableControlList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        ingredientsListView.disableProperty().bind(addProperty.not());

        medicationNameTextField.addEventHandler(KeyEvent.KEY_PRESSED,e -> {
            GUIUtility.undoDisplayFieldError(medicationNameTextField,messageLabel);
        });

    }


    public void initializeUserData(int userId) {
        this.userId = userId;
        initializeMedicationValues();
        initializeIngredientValues();
        initializeFormatValues();
        initializeMeasurementUnitValues();
        initializeListeners();
    }

    private void initializeControlEventHandlers(){

        //when the user clicks add, validate values and do save actions
        saveMedicationButton.addEventHandler(ActionEvent.ACTION,e->{
            validateValues();
            if (saveProperty.getValue()) {
                save();}
        });

        //when user clicks on edit or add call the appropriate functions
        editButton.addEventHandler(ActionEvent.ACTION,e->{setEditing();});
        addMedicationButton.addEventHandler(ActionEvent.ACTION,e->{setAdding();});


        //if edit button or add button are disabled then enable the save button because
        //we are either editing or adding information.
        disableSave = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
               return !(editButton.isDisabled() && addMedicationButton.isDisabled());
            }
        };

        // bind Save button to the above behaviour
        saveMedicationButton.disableProperty().bind(disableSave);

        /*
        * Set button disable rules for the add button
        * */
        disableAdd = new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                //if the save button is not disabled then disable the add
                 if (!saveMedicationButton.isDisabled()) return true;

                //if addProperty is set to true, then don't disable the button
                return !addProperty.getValue();
            }
        };
        //bind add button to the button rules above.
        addMedicationButton.disableProperty().bind(disableAdd);

        /*
         * Set the disable rules for the edit button.
         * */
        disableEdit = new BooleanBinding() {
            @Override
            protected boolean computeValue() {

                //if nothing is selected from the medications list then disable
                if (medicationListView
                        .getSelectionModel()
                        .getSelectedItems()
                        .size() == 0
                ) return true;

                //if user already clicked edit or add then disable until save is completed
                if (saveProperty.getValue()) return true;

                //if edit is allowed then do not disable
                return !editProperty.getValue();
            }};

        //bind add button to the above rules.
        editButton.disableProperty().bind(disableEdit);

        //set what actions can do from start
        saveProperty.set(false);
        editProperty.set(false);
        addProperty.set(true);

        //set the state flags for actions being performed
        adding = false;
        editing = false;
        deleting = false;

        medicationListView.setOnMouseClicked(e->{
            synchronizeMedicationDetails();
        });

        medicationListView.setOnKeyPressed(e->{
            synchronizeMedicationDetails();
        });

        medicationListView.setOnScroll(e->{
            synchronizeMedicationDetails();
        });
    }

    private void applyButtonRules() {
        disableSave.get();
        disableAdd.get();
        disableEdit.get();
    }

    private void synchronizeMedicationDetails() {
        Medication item = medicationListView.getSelectionModel().getSelectedItem();
        int medKey = item.getId();
        int formatKey = item.getFormatId();
        int measurementKey = item.getMeasurementId();
        ConfigureMedicationObservableData medParameters;

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
        medParameters = new ConfigureMedicationObservableData(
                medKey,formatKey,measurementKey,getUserId(),"");
        initializeMedicationIngredientsValues(medParameters);
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
            saveProperty.set(successful);
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

    private void initializeMedicationIngredientsValues(Medication medicationParameters) {

        try {
            medicationIngredientList = getOperation().getMedicationIngredients(medicationParameters);
            medicationIngredientOList = FXCollections.observableList(medicationIngredientList);
            medicationIngredientsListView.setItems(medicationIngredientOList);
            medicationIngredientsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            medicationIngredientsListView.setCellFactory(medinglist-> new MedicationIngredientCell());
        }catch (Exception e) {
            LogError.logUnrecoverableError(new OperationFailureException(e.getMessage()));
        }
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
        addProperty.set(true);
        medicationIngredientsListView.setEditable(false);
    }

    private void setEditing() {
        editing = true;
        setEditProperty(true);
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

        if (addProperty.getValue()) {
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
        if (this.getEditProperty()) {
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
            addProperty.set(false);

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
