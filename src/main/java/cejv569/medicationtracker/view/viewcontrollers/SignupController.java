package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.exceptions.UserAlreadyExistsException;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.DataValidator;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.stream.Stream;


/**
 *  The SignupController class initializes and validates issues linked with the signupform.
 *  It also mediates initiates requests for data and database storage services from the model
 *  layer.  It does this via the SignupOperation interface and it's implementing class
 *  SignupOperation.
 */

public class SignupController extends ViewController {

        // Controls
        @FXML
        private VBox signupVBox;

        @FXML
        private TextField firstNameTextField;

        @FXML
        private TextField lastNameTextField;

        @FXML
        private TextField userTextField;

        @FXML
        private PasswordField passwordTextField;

        @FXML
        private PasswordField confirmPasswordTextField;

        @FXML
        private Label messageLabel;

    @FXML
        private Button saveButton;

    //Attributes
    private cejv569.medicationtracker.model.operationinterfaces.SignupOperation signupOperation;


    //Getters & Setters

    /**
     * Getter and setters for the ViewOperation subclass, SignupOperation, attribute.
     *
     */

    public cejv569.medicationtracker.model.operationinterfaces.SignupOperation getOperation() {
        return this.signupOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.signupOperation = (cejv569.medicationtracker.model.operationinterfaces.SignupOperation)operation;
    }

    /**
     * initialize():
     * Calls the Application Controller to initialize the operation interface it must use.
     * Then loops through all the textfields and adds them to the required TextInputControl list.
     * Then calls GUIUtility.signalEmptyField which changes certain properties on the textfields in the list,
     * if one is empty.
     * adds an event handler to the saveButton that validates all the text fields in the
     * form and if they are all filled correctly calls save() save to save the information.
     */
    @FXML
        void initialize() {

        //set the operation interface object for the LoginController
        ApplicationController.getInstance().operationFactory(this);

        //collect all the text fields in the parent vbox and add them to the
        //required fields list, as none of them can be empty when the record is saved.
        Stream<Node> nodeStream = signupVBox.getChildren().stream();
        nodeStream.forEach(c -> {
            if (c instanceof TextField) {
                this.getRequiredFields().add((TextField) c);
            }
        });
        //call the function that signals to the user that the field is empty and
        //is a required field.  Do this at program start so the user gets the
        //feedback right away.
        GUIUtility.signalEmptyField(
                this.getRequiredFields(),
                GUIUtility.DEFAULT_ERROR_FIELD_BORDER_COLOR,
                GUIUtility.DEFAULT_FIELD_BORDER_COLOR,
                messageLabel,
                GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR,
                GUIUtility.DEFAULT_ERROR_FIELD_TEXTFILL_COLOR,
                UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message);

        //add event handler for save button.  Every time the button save is
        //clicked, it checks if there are empty fields to signal and if their values
        // are valid, if there are no issues,
        //then the save() process can proceed.
        saveButton.addEventHandler(ActionEvent.ACTION, e -> {
            if (allFieldsValid()) {save();}});
    }

    /**
     * fieldsValid validates each field to make sure that it isn't blank, that the user name is entered
     * as an email with the proper format and also that the password is at least 8 characters long and
     * that the password confirmation matches the password entered in the prior field.  If a field has an
     * invalid value, false is immediately returned after displaying an error message and setting the focus
     * to the field with the invalid value.  This way, each field is tested one at a time.
     * @return boolean type, returns true if all the form fields are valid and false if
     *                      at least one field is not.
     */
    private boolean allFieldsValid() {
        //check if any fields are empty, if so, signal to user
        if (!GUIUtility.signalEmptyField(
                this.getRequiredFields(),
                GUIUtility.DEFAULT_ERROR_FIELD_BORDER_COLOR,
                GUIUtility.DEFAULT_FIELD_BORDER_COLOR,
                messageLabel,
                GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR,
                GUIUtility.DEFAULT_ERROR_FIELD_TEXTFILL_COLOR,
                UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message)) {

            //if not ->

            //validate to make sure the user name is a valid email format
            if (!DataValidator.isValidUser(userTextField.getText().trim())) {
                GUIUtility.displayFieldError(userTextField,messageLabel, UserMessages.ErrorMessages.INVALID_EMAIL_MESSAGE.message);
                return false;
            }

            //validate if the password is at least 8 characters long
            if (!DataValidator.isValidPassword(passwordTextField.getText().trim())){
                GUIUtility.displayFieldError(passwordTextField,messageLabel,
                        UserMessages.ErrorMessages.INVALID_PASSWORD_MESSAGE.message);
                return false;
            }

            //make sure the password confirmation matches the password.
            if (!confirmPasswordTextField.getText().trim().equals(passwordTextField.getText().trim())) {
                GUIUtility.displayFieldError(confirmPasswordTextField,messageLabel,
                        UserMessages.ErrorMessages.PASSWORD_NOMATCH_ERROR_MESSAGE.message);
                return false;
            }

        } else {return false;}

        messageLabel.setVisible(false);
        return true;
    }



        /**
         *  save() will eventually save the user signup information to a database.  However for Assignment 4
         *  the method only loops through all the children of the signupVBox which contains all the text
         *  fields to be filled in by the user.  In the loop it validates to see if each control is a TextField
         *  type.  If it is, it will print it's text value to the console.
         *  Finally, the method uses the DisplayMessage static utility class to display a message to the user
         *  that their information has been saved.
         */
        private void save() {

            AccountObservableData accountObservableData = new AccountObservableData(
                    0,
                    firstNameTextField.getText().trim(),
                    lastNameTextField.getText().trim(),
                    userTextField.getText().trim(),
                    passwordTextField.getText().trim(),
                    "",
                    "");
            try {
                //this will generate a UserAlreadyExistsException if the username already
                //exists in the database.  If no error is generated, then the postData
                //was successful, so we can show a success message to the user.
                getOperation().postData(accountObservableData);

                //display a message to the user that their information was successfully saved.
                messageLabel.setText(UserMessages.Messages.SAVED_MESSAGE.message);

                //clear all fields of text
                GUIUtility.clearAllInputTextFields((Pane)firstNameTextField.getParent());

            }catch (UserAlreadyExistsException e) {
                LogError.logRecoverableError(e);
                messageLabel.setText(UserMessages.ErrorMessages
                        .USERNAME_ALREADY_EXISTS_MESSAGE.message);
            } catch (OperationFailureException e) {
                LogError.logUnrecoverableError(e);
            } catch (Exception e) {
                LogError.logUnrecoverableError(e);
            }
            messageLabel.setVisible(true);
        }
}

