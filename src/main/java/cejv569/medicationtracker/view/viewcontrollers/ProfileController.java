package cejv569.medicationtracker.view.viewcontrollers;


import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.view.viewdata.AccountData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 *   ProfileController is the controller class which controls the functionality of the profileForm.
 *
 */
public class ProfileController {

    //Attributes
    //Sub Controllers for each child pane in the Accordion Pane control
    private AccountController accountController;
    private ConfigureMedicationController configureMedicationController;
    private ConfigureMedicationEffectController configureMedicationEffectController;
    private EffectHistoryController effectHistoryController;
    private LogMedicationController logMedicationController;
    private LogMedicationEffectController logMedicationEffectController;
    private LogMedicationPurchaseController logMedicationPurchaseController;

    private AccountData accountData;

    @FXML
    private BorderPane profileBorderPane;

    @FXML
    private AnchorPane accountAnchorPane;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField passwordConfirmationTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private Button addAccountButton;

    @FXML
    private Button editAccountButton;

    @FXML
    private Button saveAccountButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private AnchorPane configureMedAnchorPane;

    @FXML
    private ComboBox<?> brandComboBox;

    @FXML
    private ComboBox<?> genericComboBox;

    @FXML
    private ComboBox<?> formatComboBox;

    @FXML
    private ComboBox<?> measurementComboBox;

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
    private Button editMedicationButton;

    @FXML
    private Button saveMedicationButton;

    @FXML
    private Button deleteMedicationButton;

    @FXML
    private AnchorPane effectsAnchorPane;

    @FXML
    private ComboBox<?> effectComboBox;

    @FXML
    private ComboBox<?> effectTypeComboBox;

    @FXML
    private TextArea EffectDescriptionTextArea;

    @FXML
    private CheckBox negativeCheckBox;

    @FXML
    private Button addEffectButton;

    @FXML
    private Button editEffectButton;

    @FXML
    private Button saveEffectButton;

    @FXML
    private Button deleteEffectButton;

    @FXML
    private AnchorPane medPurchaseAnchorPane;

    @FXML
    private ComboBox<?> selectBrandComboBox;

    @FXML
    private ComboBox<?> selectGenericComboBox;

    @FXML
    private Label unitsOfMeasureLabel;

    @FXML
    private TextField numberOfUnitsTextField;

    @FXML
    private TextField maxUnitsTextField;

    @FXML
    private DatePicker purchaseDatePicker;

    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private CheckBox currentlyTakingCheckBox;

    @FXML
    private CheckBox recurrentCheckBox;

    @FXML
    private CheckBox alertCheckBox;

    @FXML
    private Button addMedPurchaseButton;

    @FXML
    private Button editMedPurchaseButton;

    @FXML
    private Button saveMedPurchaseButton;

    @FXML
    private Button deleteMedPurchaseButton;

    @FXML
    private AnchorPane logMedicationAnchorPane;

    @FXML
    private ComboBox<?> brandMedTakenComboBox;

    @FXML
    private ComboBox<?> genericMedTakenComboBox;

    @FXML
    private TextField dosesTakenTextField;

    @FXML
    private DatePicker dateTakenDatePicker;

    @FXML
    private Label purchaseDateLabel;

    @FXML
    private Button addMedTakenButton;

    @FXML
    private Button editMedTakenButton;

    @FXML
    private Button saveMedTakenButton;

    @FXML
    private Button deleteMedTakenButton;

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

    //constants for fxml file paths
    private final String CONTACT_FILE_PATH = "contactForm.fxml";

    //Constants for image file paths
    private final String MAIN_IMAGE_PATH = "/cejv569/medicationtracker/assets/profilemain_img.jpg";

    //Getters and Setters

    private AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData (AccountData data) {
        this.accountData = data;
    }

    /**
     *  initialize() adds an eventhandler to the contactLabel so that the contactForm is displayed if the user
     *  clicks the contactLabel with their mouse.
     */
    @FXML
    void initialize() {
        //create and retrieve the main image for the pane background and set the pane background
        profileBorderPane.setBackground(GUIUtility.getBackgroundImage(getClass(),MAIN_IMAGE_PATH));
        createSubControllers();
    }

    public void initializeAccountData(AccountData data) {
        this.setAccountData(data);
        accountController.initializeAccountData(data);
    }

    /**
     * getContactPane() displays the contactForm, upon the user clicking the Contact Us label.  It also gets
     * the email from the emailLabel field and pre-populates the email field of the contactForm to save the user
     * some time entering it, if it's the same email they want to receive the response to their email at.
     */
    private void getContactPane() {
            try {
                Stage stage = new Stage();
                GUIUtility.openPane(stage,CONTACT_FILE_PATH, null);
//                    ((ProfileController)fxmlLoader.getController()).setEmail(userTextField.getText());
            } catch(IOException | IllegalStateException e ){
                System.err.println(e.getMessage());
            }
    }
    private void createSubControllers() {
         accountController = new AccountController(accountAnchorPane);
         configureMedicationController = new ConfigureMedicationController(configureMedAnchorPane);
         configureMedicationEffectController = new ConfigureMedicationEffectController(effectsAnchorPane);
         effectHistoryController = new EffectHistoryController(effectHistoryAnchorPane);
        logMedicationController = new LogMedicationController(logMedicationAnchorPane) ;
        logMedicationEffectController = new LogMedicationEffectController(logEffectAnchorPane);
        logMedicationPurchaseController = new LogMedicationPurchaseController(medPurchaseAnchorPane);
    }
}
