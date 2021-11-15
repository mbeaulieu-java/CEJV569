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
import cejv569.medicationtracker.view.customcellclasses.FormatCell;
import cejv569.medicationtracker.view.customcellclasses.MeasurementCell;
import cejv569.medicationtracker.view.customcellclasses.MedicationCell;
import cejv569.medicationtracker.view.customcellclasses.MedicationIngredientCell;
import cejv569.medicationtracker.view.viewdata.CheckedListViewCheckObserver;
import cejv569.medicationtracker.view.viewdata.ConfigureMedicationObservableData;
import cejv569.medicationtracker.view.viewdata.FormatObservableData;
import cejv569.medicationtracker.view.viewdata.MedicationIngredientsObservableData;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.ArrayList;
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
    private static SimpleBooleanProperty deleteProperty;

//  User Activity State Flags
    public static boolean editing;
    public static boolean adding;
    public static boolean deleting;

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

    public SimpleBooleanProperty editProperty() {
        return editProperty;
    }

    public void setEditProperty(boolean editProperty) {
        this.editProperty.set(editProperty);
    }

    public boolean getAddProperty() {
        return addProperty.get();
    }

    public SimpleBooleanProperty addProperty() {
        return addProperty;
    }

    public void setAddProperty(boolean addProperty) {
        this.addProperty.set(addProperty);
    }

    public boolean getSaveProperty() {
        return saveProperty.get();
    }

    public SimpleBooleanProperty saveProperty() {
        return saveProperty;
    }

    public void setSaveProperty(boolean saveProperty) {
        this.saveProperty.set(saveProperty);
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

        deleteProperty = new SimpleBooleanProperty(false);
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

        ingredientsListView.setDisable(true);
        //medicationIngredientsListView.setDisable(false);
        medicationIngredientsListView.setEditable(false);

    }


    public void initializeUserData(int userId) {
        this.userId = userId;
        initializeMedicationValues();
        initializeIngredientValues();
        initializeFormatValues();
        initializeMeasurementUnitValues();
        initializeListeners();

        medicationListView.getSelectionModel().selectFirst();
        synchronizeMedicationDetails();
    }

    private void initializeControlEventHandlers(){

        //if the user is allowed to save, then save
        saveMedicationButton.addEventHandler(ActionEvent.ACTION,e->{
                if (saveProperty.getValue()) {
                    save();
                }
        });

        //when user clicks on edit or add call the appropriate functions
        editButton.addEventHandler(ActionEvent.ACTION,e->{setEditing();});
        addMedicationButton.addEventHandler(ActionEvent.ACTION,e->{setAdding();});

        medicationNameTextField
                .addEventHandler(KeyEvent.ANY,e -> {onActionValidator();});

        formatListView.addEventHandler(KeyEvent.ANY,e->{onActionValidator();});
        formatListView.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{onActionValidator();});

        measurementListView.addEventHandler(KeyEvent.ANY,e->{onActionValidator();});
        measurementListView.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{onActionValidator();});


        //bind each of the buttons to the related property
        saveMedicationButton.disableProperty().bind(saveProperty.not());
        addMedicationButton.disableProperty().bind(addProperty.not());
        editButton.disableProperty().bind(editProperty.not());

        //set what actions can do from start
        saveProperty.set(false);
        editProperty.set(true);
        addProperty.set(true);

        //set the state flags for actions being performed
        adding = false;
        editing = false;
        deleting = false;

        //make sure that the details on the right pane match the medication that the user
        //has selected from their list and that it follows when the user scrolls, presses key or
        // clicks.  HOWEVER, DON'T DO IF IN SAVE MODE
        medicationListView.setOnMouseClicked(e-> {
            if (saveProperty.getValue()) {
                editProperty.set(false);
                addProperty.set(false);
            } else {
                editProperty.set(true);
                addProperty.set(true);
                synchronizeMedicationDetails();
            }
        });

        medicationListView.setOnKeyPressed(e->{
            if (saveProperty.getValue()) {
                editProperty.set(false);
                addProperty.set(false);
            } else {
                editProperty.set(true);
                addProperty.set(true);
                synchronizeMedicationDetails();
            }
        });

        medicationListView.setOnScroll(e->{
            if (saveProperty.getValue()) {
                editProperty.set(false);
                addProperty.set(false);
            } else {
                editProperty.set(true);
                addProperty.set(true);
                synchronizeMedicationDetails();
            }
        });

    }

    private void onActionValidator() {
        //if all three buttons (add, edit and save are disabled) then check to validate
        //the values.  If all good, activate the save
        if (    !addProperty.getValue() &&
                !editProperty.getValue()
        )
        {
            //set allowed to save if the all the values are valid
            if (validateValues()) {
                saveProperty.set(true);
            } else {
                saveProperty.set(false);
            }
        }
    }

    //filters and displays the correct data that corresponds to the selected medication
    //in the users' medication list
    private void synchronizeMedicationDetails() {
        //get the selected medication
        Medication item = medicationListView.getSelectionModel().getSelectedItem();
        int medKey = item.getId();
        int formatKey = item.getFormatId();
        int measurementKey = item.getMeasurementId();
        ConfigureMedicationObservableData medParameters;

        //set text box to med name
        medicationNameTextField.textProperty().bind(
                ((ConfigureMedicationObservableData)medicationListView
                        .getSelectionModel()
                        .getSelectedItem())
                        .nameProperty());

        //set format to right format

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

        //set measurement unit to right unit
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

        //call the database to retrieve the records for the medication ingredients corresponding
        //to the selected medication.
        medParameters = new ConfigureMedicationObservableData(
                medKey,formatKey,measurementKey,getUserId(),"");
        initializeMedicationIngredientsValues(medParameters);
    }

    //add any change listeners
    private void initializeListeners() {
        medicationIngredientsListView.refresh();

        //add a listener to detect if the user removes a medication ingredient node
        //from the medication ingredients list view.  If so, then delete the medication
        //ingredient for that medication from the list.
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

    //validate that all the information is entered correctly into the fields.
    /*  A medication name must be entered,a format and measurement unit selected too */
    private boolean validateValues() {

        if (medicationNameTextField.getText().trim().isBlank()) return false;

        if (formatListView.getSelectionModel().getSelectedItems().size() == 0) return false;

        if (measurementListView.getSelectionModel().getSelectedItems().size() == 0) return false;

        return true;
    }

    //retrieve all the medication records from the database, create observable data objects
    //for them and add them to the medicationsListView
    private void initializeMedicationValues() {
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

            medicationOList = FXCollections.observableList(observervableDataList);
            medicationListView.setItems(medicationOList);
            medicationListView.setCellFactory(medlist-> new MedicationCell());
        }catch (Exception e) {
            System.err.println(e);
            LogError.logUnrecoverableError(new OperationFailureException(e.getMessage()));
        }
    }

    //Re-initializes the Medication Ingredient values in the medicationIngredientsListView
    //according to whichever medication is selected in the medicationListView (user's medication list).
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

    //gets the data from the formats table of the database, creates observable data for them
    //and initializes the formatListView.
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

    //Retrieves the measurement unit values from the database and loads them into the
    //measurementListView
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
    //gets the list of ingredients from the database and loads them into the
    //ingredientsListView
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

    //The ingredientsListView uses CheckBoxListCell, so the cell parameters must be initialized
    private void setIngredientListParameters(){
        //load items into lost & set list to allow multiselect
        ingredientsListView.setItems(ingredientOList);
        ingredientsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //set a converter object that sets the textbox text for each ingredient in list
        //to ingredient name
            converter = new StringConverter<Ingredient>() {
            @Override
            public String toString(Ingredient object) {
                return object.getName();
            }

                //return the proper ingredient object given it's string name
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

            //create an object that will pair a boolean Observable property with
            // each ingredient node of the list (since there are no inbuilt ones to
            //support binding the checked or unchecked state of the associated checkbox).
        checkedListCellobserver = new CheckedListViewCheckObserver<Ingredient>();

        //set a listener to be advised everytime a user checks or unchecks a value in the
        //list.
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

    //prepare the various rules and states to indicate that the information is in Add mode
    private void setAdding() {

        //user not allowed to add, they are already adding
        addProperty.set(false);
        editProperty.set(false);

        //State is adding
        adding = true;

        //clear all controls so the user can enter new information

        //first remove the binding on the medication name textfield
        medicationNameTextField.textProperty().unbind();
        //loop through editable controls and reset
        for (Control c :editableControlList) {
            if (c instanceof TextField){
                ((TextField)c).setText("");
            }
            if(c instanceof ListView) {
                ((ListView)c).getSelectionModel().clearSelection();
            }
        }
        //re-initialize the ingredients list values to remove all the prior checked items
        this.initializeIngredientValues();

        //make it selectable
        ingredientsListView.setDisable(false);

        //make nothing selected in the list
        medicationListView.getSelectionModel().clearSelection();
        medicationIngredientsListView.getItems().clear();

        //if adding medications, the user can't delete items from the medication list
        //medicationIngredientsListView.setDisable(true);

        try {
            //make all required fields editable
            GUIUtility.makeEditable(editableControlList);
        } catch (Exception e){ LogError.logRecoverableError(e);}

        //set allowed to save if the all the values are valid
        if (validateValues()) {
            saveProperty.set(true);
        } else {
            saveProperty.set(false);
        }
    }
        //set various items for edit mode
    private void setEditing() {

        //set flags

        //can't edit if already editing
        editProperty.set(false);

        //can't add if editing
        addProperty.set(false);

        //editing state
        editing = true;


        //allowed to delete medication ingredients
        deleteProperty.set(true);


        try {
            //Enable edits to the medicationingredients list view so user can delete or add
            //ingredients.

            //make the controls editable.
            GUIUtility.makeEditable(editableControlList);
            //set text box to med name
            medicationNameTextField.textProperty().unbind();

            //make it selectable
            ingredientsListView.setDisable(false);

            //add the delete change listener so we can track nodes removed from the list
            medicationIngredientsListView.getItems().addListener(deleteIngredientListener);

            //set allowed to save if the all the values are valid
            if (validateValues()) {
                saveProperty.set(true);
            } else {
                saveProperty.set(false);
            }

        } catch (Exception e) {
            LogError.logRecoverableError(e);
        }
    }

    //do save, redirect to the right procedure depending on whether it's an add or edit
    private void save() {
        Ingredient key;
        int index = 0;
        SimpleObjectProperty<Pair<Ingredient,Boolean>> temp2;
        boolean successful = false;

        //if state is adding then do add
        if (adding) {successful = doAdd();}

        //if state is editing then do edit
        if (editing) {
            successful = doEdit();

            if (successful) {
                //after the edit, remove the ability for the user to delete an ingredient until they
                //click edit again.
                medicationIngredientsListView
                        .getItems().
                        removeListener(deleteIngredientListener);
            }
        }
        if (successful) {
            try {
                //disactivate the save button until next action
                saveProperty.set(false);
                // user can't delete until next edit
                deleteProperty.set(false);

                //After add, the user can add & edit again
                addProperty.set(true);
                editProperty.set(true);

                 //save any editing or adding state to false
                adding = false;
                editing = false;

                //make all required fields un-editable until next add or edit
                GUIUtility.makeUnEditable(editableControlList);
                //set text box to med name
                medicationNameTextField.textProperty().bind(
                        ((ConfigureMedicationObservableData)medicationListView
                                .getSelectionModel()
                                .getSelectedItem())
                                .nameProperty());

                //re-initialize the ingredients list values to remove all the prior checked items
                this.initializeIngredientValues();
                setIngredientListParameters();
                ingredientsListView.setDisable(true);

            } catch (Exception e) {LogError.logRecoverableError(e);}
        }
    }

    //perform edits to the medication data
    private boolean doEdit(){

        boolean successful = false;
        Medication medicationData = null;
        List<MedicationIngredients> newMedIngredients = null;
        int medKey, formatKey,measurementKey,selectedIndex = 0;

        try {
            //if for some reason the medication is no longer selected then can't be sure
            //of the data we're posting so do not perform save
            if(medicationListView
                    .getSelectionModel()
                    .getSelectedItems()
                    .size() == 0) return false;

            //get the selected index to be able to position the modified node back into
            //the list correctly sorted.
            selectedIndex = medicationListView.getSelectionModel().getSelectedIndex();
            //get the Primary Keys for the Medication being edited
            medKey = medicationListView.getSelectionModel().getSelectedItem().getId();
            formatKey = formatListView.getSelectionModel().getSelectedItem().getId();
            measurementKey = measurementListView.getSelectionModel().getSelectedItem().getId();

            //create data object to post the data
            medicationData = new ConfigureMedicationObservableData(
                    medKey,
                    formatKey,
                    measurementKey,
                    getUserId(),
                    medicationNameTextField.getText().trim());

            //post the edited record to the database
            getOperation().putMedication(medicationData);

            //remove the old medication record, then go through the list comparing the
            //name of the modified record to each in the list to know where to put it
            //so it's sorted.  Select the item and scroll to it so the user sees the modifications.
            medicationListView.getItems().remove(selectedIndex);

            for (int x = 0; x < medicationListView.getItems().size();x++){
                if (medicationData.getName()
                        .compareToIgnoreCase(
                                medicationListView
                                .getItems().
                                get(x).getName()) < 0)
                {
                    medicationListView.getItems().add(x,medicationData);
                    medicationListView.getSelectionModel().select(x);
                    medicationListView.scrollTo(x);
                    break;
                }
            }

            //if there are ingredients that were selected to add, then add them

            //build data objets list to do save
            if (selectedIngredients.size() != 0) {
                List<MedicationIngredients> ingredients = new ArrayList<>();
                for (Ingredient i : selectedIngredients) {

                    MedicationIngredients ing = new MedicationIngredientsObservableData(
                            0,
                            medKey,
                            i.getId(),
                            i.getName());

                    ingredients.add(ing);
                }
                //update the records in the database
                newMedIngredients = getOperation().putMedicationIngredients(ingredients);

                //if new ingredients were added to the DB then add new ingredients to the
                //medication ingredient list,then clear selected list for future actions.
                if (newMedIngredients != null && !newMedIngredients.isEmpty()) {
                    medicationIngredientsListView.getItems().addAll(newMedIngredients);
                    selectedIngredients.clear();
                    newMedIngredients.clear();
                }
            }


            //save successful and no longer allowed to save again until another transaction
            //is initiated.
            successful = true;
            saveProperty.set(false);
            //re-synchronize the medication details to reflect the changes.
            synchronizeMedicationDetails();

            //if a medication with same name, format and measurment unit already saved,
            //generate an error
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

    // add new medication records
    private boolean doAdd(){

        Medication medicationData = null;
        int addMedKey = 0;
        boolean successful = false;

        List<MedicationIngredients> newMedIngredients;
        try {
            //create data object to post the data
            medicationData = new ConfigureMedicationObservableData(
                    0,
                    formatListView.getSelectionModel().getSelectedItem().getId(),
                    measurementListView.getSelectionModel().getSelectedItem().getId(),
                    getUserId(),
                    medicationNameTextField.getText().trim());

            //post the data and get the primary key for the new added medication record
            //to be able to post the added medication ingredients.
            addMedKey = getOperation().postMedication(medicationData);

            //if no primary key returned there was a problem
            if (addMedKey == 0) {throw new OperationFailureException(("An error during the " +
                    "process of adding the new medication."));}

            medicationData.setId(addMedKey);

            //add the new medication record into the list sorted by name, select it and
            //scroll to it so the user can see it was added.

            for (int x = 0; x < medicationListView.getItems().size();x++){
               if (medicationData.getName().compareToIgnoreCase(medicationListView.getItems().get(x).getName()) < 0) {
                   medicationListView.getItems().add(x,medicationData);
                   medicationListView.getSelectionModel().select(x);
                   medicationListView.scrollTo(x);
                   break;
               }
            }

            //if there are ingredients that were selected to add then add them
            //build data objets list to do save
            if (selectedIngredients.size() != 0) {
                List<MedicationIngredients> ingredients = new ArrayList<>();
                for (Ingredient i : selectedIngredients) {
                    MedicationIngredients ing = new MedicationIngredientsObservableData(
                            0,addMedKey,i.getId(),i.getName());
                    ingredients.add(ing);
                }
                //post data to database
                newMedIngredients = getOperation().postMedicationIngredients(ingredients);
                //if new ingredients were added then add new med ingredients to the
                //medication ingredient list,then clear selected list for future actions.
                if (newMedIngredients != null && !newMedIngredients.isEmpty()) {
                    medicationIngredientsListView.getItems().addAll(newMedIngredients);
                    selectedIngredients.clear();
                    newMedIngredients.clear();
                }
            }
            //save successful and no longer allowed to save again until another transaction
            //is initiated.
            successful = true;
            saveProperty.set(false);

            //if a medication with same name, format and measurment unit already saved,
            //generate an error
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

    // Delete Medication Ingredients
    public boolean doMedicationIngredientDelete(MedicationIngredients medicationIngredient){
        boolean successful = false;
        //set state to delete
        deleting = true;
        //send the data to be deleted in the database
        try {
            configureMedicationOperation.deleteMedicationIngredients(medicationIngredient);

            //remove the deleted item from the list
            getMedicationIngredientOList().remove(medicationIngredient);
        } catch (Exception e) {
            LogError.logUnrecoverableError(
                    new OperationFailureException("Error occured during delete. Error message " + e.getMessage()));
        }
        deleting = false;
        return successful;
    }


}
