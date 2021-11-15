package cejv569.medicationtracker.view.viewcontrollers;


import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.operationinterfaces.AccountOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.DataValidator;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class AccountController extends ViewController {

    //Controls

    @FXML
    private TitledPane accountTitledPane;

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
    private Button editAccountButton;

    @FXML
    private Button saveAccountButton;

    @FXML
    private Label messageLabel;

    private AccountObservableData accountObservableData;
    private AccountOperation accountoperation;
    private List<TextInputControl> requiredFields;

    //Getter and Setters
    private AccountObservableData getAccountData() {
        return accountObservableData;
    }

    private void setAccountData(AccountObservableData accountObservableData) {
        this.accountObservableData = accountObservableData;
    }

    public AccountOperation getOperation() {
        return accountoperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.accountoperation = (AccountOperation) operation;
    }

    public void initializeAccountData(AccountObservableData accountObservableData) {
        setAccountData(accountObservableData);
        firstNameTextField.textProperty().bindBidirectional(this.accountObservableData.firstNameProperty());
        lastNameTextField.textProperty().bindBidirectional(this.accountObservableData.lastNameProperty());
        emailTextField.textProperty().bindBidirectional(this.accountObservableData.emailProperty());
        telephoneTextField.textProperty().bindBidirectional(this.accountObservableData.telephoneProperty());
        userTextField.textProperty().bindBidirectional(this.accountObservableData.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(this.accountObservableData.passwordProperty());
        passwordConfirmationTextField.setText(passwordTextField.getText());
    }

    private void doSave() {
        if (validateAccountData()) {
            updateAccountData();
            passwordConfirmationTextField.setDisable(true);
            messageLabel.setVisible(true);
            messageLabel.setText(UserMessages.Messages.SAVED_MESSAGE.message);
        }
    }

    private boolean validateAccountData() {
        messageLabel.setVisible(false);
        if (GUIUtility.signalEmptyField(requiredFields)) {
            GUIUtility.displayFieldError(userTextField,messageLabel, UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message);
            return false;
        }
        //validate to make sure the user name is a valid email format
        if (!DataValidator.isValidUser(userTextField.getText().trim())) {
            GUIUtility.displayFieldError(userTextField,messageLabel, UserMessages.ErrorMessages.INVALID_EMAIL_MESSAGE.message);
            return false;
        }

        //validate to make sure the user name is a valid email format
        if (!DataValidator.isValidUser(emailTextField.getText().trim())) {
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
        if (!passwordConfirmationTextField.getText().trim().equals(passwordTextField.getText().trim())) {
            GUIUtility.displayFieldError(passwordConfirmationTextField,messageLabel,
                    UserMessages.ErrorMessages.PASSWORD_NOMATCH_ERROR_MESSAGE.message);
            return false;
        }

        messageLabel.setVisible(false);
        return true;
    }

    private void updateAccountData() {
        try {
            getOperation().putData(getAccountData());
        } catch (OperationFailureException e) {
            //display error message

        }
    }

    @FXML
    void initialize() {

        //initialized required fields
        requiredFields = new ArrayList<>();
        requiredFields.add(firstNameTextField);
        requiredFields.add(lastNameTextField);
        requiredFields.add(emailTextField);
        requiredFields.add(telephoneTextField);
        requiredFields.add(userTextField);
        requiredFields.add(passwordTextField);


        passwordTextField.setOnKeyPressed(e->{
            passwordConfirmationTextField.setDisable(false);
        });


        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);
        saveAccountButton.addEventHandler(ActionEvent.ACTION, e -> {
            doSave();
        });
    }

}