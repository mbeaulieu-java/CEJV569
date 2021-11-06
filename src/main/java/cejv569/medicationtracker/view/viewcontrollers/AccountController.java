package cejv569.medicationtracker.view.viewcontrollers;


import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.AccountOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.viewdata.AccountObservableData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.stream.Stream;

public class AccountController extends ViewController{

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

    private AccountObservableData accountObservableData;
    private AccountOperation accountoperation;

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
        this.accountoperation = (AccountOperation)operation;
    }

    public void initializeAccountData(AccountObservableData accountObservableData) {
        setAccountData(accountObservableData);
        firstNameTextField.textProperty().bindBidirectional(this.accountObservableData.firstNameProperty());
        lastNameTextField.textProperty().bindBidirectional(this.accountObservableData.lastNameProperty());
        emailTextField.textProperty().bindBidirectional(this.accountObservableData.emailProperty());
        telephoneTextField.textProperty().bindBidirectional(this.accountObservableData.telephoneProperty());
        userTextField.textProperty().bindBidirectional(this.accountObservableData.userNameProperty());
        passwordTextField.textProperty().bindBidirectional(this.accountObservableData.passwordProperty());
    }

    @FXML
    void initialize() {

        //set the operation interface object for the AccountController
        ApplicationController.getInstance().operationFactory(this);

    }

}
