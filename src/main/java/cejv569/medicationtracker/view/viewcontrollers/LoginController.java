package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.MedTrack;
import cejv569.medicationtracker.exceptions.NoSuchUserNameException;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.WrongPasswordException;
import cejv569.medicationtracker.model.operationinterfaces.LoginOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.DataValidator;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.viewdata.AccountData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
     * Resource Paths constants
     */
    //relative path for the button graphics
    private final String LOGIN_IMAGE_PATH = "/cejv569/medicationtracker/assets/login.png";
    private final String SIGNUP_IMAGE_PATH = "/cejv569/medicationtracker/assets/signup.png";
    private final String CONTACT_IMAGE_PATH = "/cejv569/medicationtracker/assets/contact.png";

    /**
     * Constants for FXML files
     */

    private final String CONTACT_FILE_PATH = "contactForm.fxml";
    private final String PROFILE_FILE_PATH = "profileForm.fxml";
    private final String SIGNUP_FILE_PATH = "signupForm.fxml";



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
    private AccountData accountData;

    //Setters & Getters

    /* Setter and getter for an AccountData type property which stores
    * any data retreived from the database upon login prior to displaying the profileform.
    * It is used to initialize the fields in the Account subpanel in the profileform. */
    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
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
     *  initialize()
     *  1) Initializes it's operation attribute via a call to ApplicationController ...operationFactory.
     *  Then sets the background graphic for the login and signup buttons.
     *  Calls the method required to initiate value validation processing for the text fields
     *  on the form.
     *  After which it assigns event handlers to all the buttons.
     */
    @FXML
    void initialize() {
        //set the operation interface object for the LoginController
        ApplicationController.getInstance().operationFactory(this);

      //create the background image and set the background for the loginButton
        loginButton.setBackground(GUIUtility.getBackgroundImage(getClass(),LOGIN_IMAGE_PATH));

        //create the background image and set the background for the signupButton
        signupButton.setBackground(GUIUtility.getBackgroundImage(getClass(),SIGNUP_IMAGE_PATH));

        //create the background image and set the background for the ContactButton
        contactButton.setBackground(GUIUtility.getBackgroundImage(getClass(),CONTACT_IMAGE_PATH));

        initFieldValidation();

        // Add event handlers for buttons
        loginButton.addEventHandler(ActionEvent.ACTION,(e)->{isValidLogin();});
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
        accountData = null;

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
                        accountData = getOperation().getAccountData(userId);
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

                if(accountData != null ) {

                    messageLabel.setVisible(false);
                    try {
                        Stage stage = new Stage();

                        //pass in account data to Profile Controller
                        //This allows the scene to pass the accountData to the AccountController instance
                        //so it can initialize it's controls with the user account data.

                        FXMLLoader fxmlLoader = new FXMLLoader(MedTrack.class.getResource(PROFILE_FILE_PATH));
                        Scene scene = new Scene(fxmlLoader.load());
                        ((ProfileController)fxmlLoader.getController()).initializeAccountData(accountData);
                        stage.setTitle("MedTrack");
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.showAndWait();

                        //re-set account data to null for next login attempt if user comes back to
                        //login screen.
                        accountData = null;
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
}