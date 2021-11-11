package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.MedTrack;
import cejv569.medicationtracker.exceptions.NoSuchUserNameException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.WrongPasswordException;
import cejv569.medicationtracker.model.datainterfaces.User;
import cejv569.medicationtracker.model.operationinterfaces.LoginOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.DataValidator;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 *  LoginController controls all the validation and functionality of the loginForm.  It's main purpose is
 *  to validate the user and password of the user and, if these are correct, to open the profileForm.  The
 *  controller also opens the contactForm is the user clicks on the Contact Us button. Finally,
 *  if the user does not have user created, the user can sign up using the signup button (button displaying
 *  signup.png graphic) to display the signupForm.
 */
public class LoginController extends ViewController{


    /**
     * Constants for FXML files
     */

    private final String CONTACT_FILE_PATH = "contactForm.fxml";
    private final String PROFILE_FILE_PATH = "profileForm.fxml";
    private final String SIGNUP_FILE_PATH = "signupForm.fxml";
    private final String ACCOUNT_FILE_PATH = "accounttitledpane.fxml";
    private final String ADD_MED_PURCHASE_FILE_PATH = "addmedpurchasetitledpane.fxml";
    private final String CONFIG_EFFECTS_FILE_PATH = "configureeffectstitledpane.fxml";
    private final String CONFIG_MED_FILE_PATH = "configuremedtitledpane.fxml";
    private final String LOG_MED_EFFECT_FILE_PATH = "logmedeffecttitledpane.fxml";
    private final String LOG_MED_FILE_PATH = "logmedicationtitledpane.fxml";
    private final String MED_EFFECTS_HISTORY_FILE_PATH = "medeffectshistorytitledpane.fxml";




    @FXML
    private Button loginButton;

    @FXML
    private Button contactButton;

    @FXML
    private Button signupButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField userTextField;

    @FXML
    private Label messageLabel;

    //Attributes
    private LoginOperation loginOperation;
    private AccountObservableData accountObservableData;

    //Setters & Getters

    /* Setter and getter for an AccountObservableData type property which stores
    * any data retreived from the database upon login prior to displaying the profileform.
    * It is used to initialize the fields in the Account subpanel in the profileform. */
    public User getAccountData() {
        return accountObservableData;
    }

    public void setAccountData(AccountObservableData accountObservableData) {
        this.accountObservableData = accountObservableData;
    }


    /**
     *  Getter and Setter for the Operation interface, LoginOperation that this view controller
     *  uses to communicate with the model layer.
     *
     */
    public LoginOperation getOperation() {
        return this.loginOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.loginOperation = (LoginOperation)operation;
    }

    /**
     *  initialize():
     *  Calls the Application Controller to initialize the operation interface it must use.
     * It then calls GUIUtility.signalEmptyField which changes certain properties on the textfields in the list,
     *  if one is empty.
     *  After which it assigns event handlers to all the buttons.
     */
    @FXML
    void initialize() {
        //set the operation interface object for the LoginController
        ApplicationController.getInstance().operationFactory(this);

        //set the required fields list and call the functions that change the TextInputField
        //properties to denote that the field is empty.
        initFieldValidation();

        // Add event handlers for buttons
        loginButton.addEventHandler(ActionEvent.ACTION,(e)->{
            userTextField.setText("m@g.com");
            passwordTextField.setText("12345678");
            isValidLogin();
//            try {
//                showProfilePane();
//            } catch(IOException err) {
//                System.err.println(err);
//            }
        });
        contactButton.addEventHandler(ActionEvent.ACTION,(e)->{getContactPane();});
        signupButton.addEventHandler(ActionEvent.ACTION,(e)->{getSignUpPane();});
    }

    /**
     * isValidLogin first uses the GUIUtility.signalEmptyFiels to validate if there are any empty fields
     * that are required to be filled by the user.  If so, the fields will appear with a red border and
     * a error message will be displayed informing the user that the fields can't be left blank.
     * Once there are no longer empty fields, the function validates that the user name and password meet
     * the required format for the app.  If this is valid, then a request will be passed on to the
     * model layer to verify if the user name and password entered by the user are in the database
     * and match the values registered prior to the login event.  If any of the information is not
     * correct, an error will be thrown by the model layer which must be caugh by the login controller
     * who handles it by displaying an error message to the user.  If no error is generated, then
     * the login was successful and both the user and login were good.  If such is the case,
     * the LoginController then opens the profileForm.
     */
    public boolean isValidLogin(){

        int userId = 0;
        User userData = null;

        //call the function that signals to the user that the field is empty and
        //is a required field.  Need to check before login validation of user and
        //password.
        if (GUIUtility.signalEmptyField(
                this.getRequiredFields(),
                GUIUtility.DEFAULT_ERROR_FIELD_BORDER_COLOR,
                GUIUtility.DEFAULT_FIELD_BORDER_COLOR,
                messageLabel,
                GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR,
                GUIUtility.DEFAULT_ERROR_FIELD_TEXTFILL_COLOR,
                UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message))
        {return false;}

        if (DataValidator.isValidUser(userTextField.getText())) {
            if(DataValidator.isValidPassword(passwordTextField.getText())) {
                try {
                    userId = getOperation().userAndPasswordExists(
                            userTextField.getText().trim(),
                            passwordTextField.getText().trim());

                    if (userId != 0) {
                        GUIUtility.undoDisplayFieldError(getRequiredFields(),messageLabel);
                        userData = getOperation().getAccountData(userId);
                        accountObservableData = new AccountObservableData(userData.getId(),userData.getFirstName(),
                                userData.getLastName(),userData.getUserName(),userData.getPassword(),
                                userData.getEmail(),userData.getTelephone());

                    }

                } catch (NoSuchUserNameException e) {
                    //HANDLE ERROR MESSAGE TO USER for Wrong User Name
                    GUIUtility.displayFieldError(userTextField,messageLabel,
                            UserMessages.ErrorMessages.WRONG_USERNAME_ERROR_MESSAGE.message);
                } catch (WrongPasswordException e) {

                    //remove any user name error effects on controls
                    GUIUtility.undoDisplayFieldError(userTextField,messageLabel);

                    //HANDLE ERROR MESSAGE TO USER for Wrong Password for this User Name
                    GUIUtility.displayFieldError(passwordTextField,messageLabel,
                            UserMessages.ErrorMessages.WRONG_PASSWORD_ERROR_MESSAGE.message);

                }catch (OperationFailureException e) {
                    LogError.logUnrecoverableError(e);
                }

                if(accountObservableData != null ) {

                    messageLabel.setVisible(false);
                    try {
                        showProfilePane();
                        //re-set account data to null for next login attempt if user comes back to
                        //login screen.
                        accountObservableData = null;
                        //call function to clear login user name and password in text fields
                        GUIUtility.clearAllInputTextFields((Pane)userTextField.getParent());
                        //re-initialize styling to signal empty fields to the user
                        initFieldValidation();


                    } catch(IOException | IllegalStateException e ){
                        LogError.logUnrecoverableError(e);
                        return false;
                    }
                }
            }else {
                    GUIUtility.displayFieldError(passwordTextField,messageLabel,
                            UserMessages.ErrorMessages.INVALID_PASSWORD_MESSAGE.message);
                    return false;
            }
        } else {
            GUIUtility.displayFieldError(userTextField,messageLabel,
                    UserMessages.ErrorMessages.INVALID_EMAIL_MESSAGE.message);
            return false;
        }

        //if all is good and the profile pane was opened return true;
        return true;
    }

    /**
     *  getContactPane() displays the contactForm.fxml if the user clicks the contactButton button.
     */
    public void getContactPane(){

        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            GUIUtility.openPane(stage,CONTACT_FILE_PATH, null);
            //call function to clear login user name and password in text fields
            GUIUtility.clearAllInputTextFields((Pane)userTextField.getParent());
            //re-initialize styling to signal empty fields to the user
            initFieldValidation();
        } catch(IOException | IllegalStateException e ){
            LogError.logUnrecoverableError(e);
        }
    }

    /**
     *  getSignUpPane() displays the signupForm.fxml if the user clicks the signupButton button.
     */
    public void getSignUpPane(){

        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            GUIUtility.openPane(stage,SIGNUP_FILE_PATH, null);
            initFieldValidation();
        } catch(IOException | IllegalStateException e ){
            LogError.logUnrecoverableError(e);
        }
    }

    /**
     * initFieldValidation initializes the parent requiredFields attribute, which is a list of
     * the TextInputControls on the form, which are used to accept input from the user, that must
     * return certain information that the application requires.  Thus, the method loads
     * usertextfield and passwordtextfield to the list so that they can be validated by utility
     * functions to make sure the required data is input and valid.
     */
    public void initFieldValidation() {
        getRequiredFields().clear();

        //add required text fields to required text fields list for validation to signal to
        //user any empty fields.
        getRequiredFields().add(userTextField);
        getRequiredFields().add(passwordTextField);

        //call the function that signals to the user that the field is empty and
        //is a required field.  Do this at program start or after the process requiring validation
        //has been compelted and then re-initialize the fields so the user gets the feedback right away.
        GUIUtility.signalEmptyField(
                this.getRequiredFields(),
                GUIUtility.DEFAULT_ERROR_FIELD_BORDER_COLOR,
                GUIUtility.DEFAULT_FIELD_BORDER_COLOR,
                messageLabel,
                GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR,
                GUIUtility.DEFAULT_ERROR_FIELD_TEXTFILL_COLOR,
                UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message);

    }
    public void showProfilePane () throws IOException {

        Accordion accordion = null;
        Stage stage = new Stage();
        Scene scene = null;

        //pass in account data to Profile Controller
        //This allows the scene to pass the accountObservableData to the AccountController instance
        //so it can initialize it's controls with the user account data.

        FXMLLoader rootFxmlLoader =
                new FXMLLoader(MedTrack.class.getResource(PROFILE_FILE_PATH));
        BorderPane profilePane = (BorderPane)rootFxmlLoader.load();

        FXMLLoader accountFxmlLoader =
                new FXMLLoader(MedTrack.class.getResource(ACCOUNT_FILE_PATH));
        TitledPane accountPane = (TitledPane)accountFxmlLoader.load();
        ((AccountController)accountFxmlLoader.getController()).initializeAccountData(accountObservableData);

        FXMLLoader configureMedFxmlLoader = new FXMLLoader(MedTrack.class.getResource(CONFIG_MED_FILE_PATH));
        TitledPane configureMedPane = (TitledPane)configureMedFxmlLoader.load();
        ((ConfigureMedicationController)configureMedFxmlLoader.getController()).initializeUserData(accountObservableData.getId());

        /**
         * THESE PANES WILL BE ADDED LATER AND ARE NOT PART OF THIS PROJECT
         */
//        FXMLLoader configureEffectsFxmlLoader = new FXMLLoader(MedTrack.class.getResource(CONFIG_EFFECTS_FILE_PATH));
//        TitledPane configureEffectsPane = (TitledPane)configureEffectsFxmlLoader.load();
//
//        FXMLLoader addMedPurchaseFxmlLoader =
//                new FXMLLoader(MedTrack.class.getResource(ADD_MED_PURCHASE_FILE_PATH));
//        TitledPane addMedPurchasePane = (TitledPane)addMedPurchaseFxmlLoader.load();
//
//        FXMLLoader logMedicationFxmlLoader = new FXMLLoader(MedTrack.class.getResource(LOG_MED_FILE_PATH));
//        TitledPane logMedicationPane = (TitledPane)logMedicationFxmlLoader.load();
//
//        FXMLLoader logMedEffectFxmlLoader = new FXMLLoader(MedTrack.class.getResource(LOG_MED_EFFECT_FILE_PATH));
//        TitledPane logMedEffectPane = (TitledPane)logMedEffectFxmlLoader.load();
//
//        FXMLLoader medEffectsHistoryFxmlLoader = new FXMLLoader(MedTrack.class.getResource(MED_EFFECTS_HISTORY_FILE_PATH));
//        TitledPane medEffectsHistoryPane = (TitledPane)medEffectsHistoryFxmlLoader.load();

        accordion = (Accordion)profilePane.getChildren().get(1);

         accordion.getPanes().add(accountPane);
         accordion.getPanes().add(configureMedPane);

         //TO BE ADDED LATER, NOT PART OF THIS PROJECT
//         accordion.getPanes().add(configureEffectsPane);
//         accordion.getPanes().add(addMedPurchasePane);
//         accordion.getPanes().add(logMedicationPane);
//         accordion.getPanes().add(medEffectsHistoryPane);


        scene = new Scene(profilePane);
         stage.setTitle("MedTrack");
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.showAndWait();
    }
}