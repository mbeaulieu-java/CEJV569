package cejv569.medicationtracker.view.viewcontrollers;


import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.view.viewdata.AccountData;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AccountController extends ViewController{

    public Pane rootPane;
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
    private Button editAccountButton;

    @FXML
    private Button saveAccountButton;



    public AccountController(Pane rootPane) {
        //set the operation interface object for the LoginController
        ApplicationController.getInstance().operationFactory(this);
        this.rootPane = rootPane;
    }

    //Getter and Setters


    private AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public ViewOperation getOperation() {
        return super.operation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public void initializeAccountData(AccountData accountData) {
        setAccountData(accountData);
        Scene accountScene = new Scene(getRootPane());
//        //TRY to FIGURE oUT WAY TO GET IT ON SEPERATE FORM
//        getRootPane().getParent().getParent().
//
//        firstNameTextField.setText(accountData.getFirstName());
        System.out.println(
                accountData.getUserName() + "\n " +
                        accountData.getPassword() + "\n" +
                        accountData.getLastName() + "\n" +
                accountData.getEmail() + "\n" +
                        accountData.getTelephone());
    }
}
