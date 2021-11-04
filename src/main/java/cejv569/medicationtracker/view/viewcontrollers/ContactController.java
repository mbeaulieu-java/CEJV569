package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.Contact;
import cejv569.medicationtracker.model.operationinterfaces.ContactOperation;
import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import cejv569.medicationtracker.utility.DataValidator;
import cejv569.medicationtracker.utility.GUIUtility;
import cejv569.medicationtracker.utility.LogError;
import cejv569.medicationtracker.utility.UserMessages;
import cejv569.medicationtracker.view.viewdata.ContactObservableData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *  The ContactController class directs the functionality of the contactForm.  It thus validates the
 *  information input by the user into the contact fields and provides feedback messages to the user concerning
 *  the input data.
 */
public class ContactController extends ViewController{

    /**
     * Resource Paths constants
     */
    //relative path for the submit button graphic
    private final String SUBMIT_IMAGE_PATH = "/cejv569/medicationtracker/assets/submit.png";

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    //Attributes
    private ContactOperation contactOperation;

    /**
     * Setters and Getters for class attributes
     */

    /*
    * Sets and gets the class which implements the ContactOperation interface.
    * This interface and it's implementation class communicate with the model layer of the
    * app allowing requests to go through the model layer and from there to the database layer.
    * */
    public ContactOperation getOperation() {
        return contactOperation;
    }

    @Override
    public void setOperation(ViewOperation operation) {
        super.operation = operation;
        this.contactOperation = (ContactOperation)operation;
    }

    /**
     *  initialize() sets the background graphic for the submit button and adds an event handler for it.
     *  The event handler class the isValidInfo to validate the information before it is submitted.
     */
    @FXML
    void initialize() {

        //Assign the Operation Interface for this Controller
        ApplicationController.getInstance().operationFactory(this);
        //init all the required fields so the user gets the feedback about which fields are
        //required.
        initFieldValidation();
        //set submitButton background image
        submitButton.setBackground(GUIUtility.getBackgroundImage(getClass(),SUBMIT_IMAGE_PATH));
        //add button handler
        submitButton.addEventHandler(ActionEvent.ACTION,(e)->{isValidInfo();});
    }


    /**
     * isValidInfo() uses GUIUtility signalEmptyField to pass in the controllers list of required fields.
     * Thus the function will change the border color of the fields and display a message that all
     * required fields must be filled.  If there are no fields left empty, then verify the
     * email has been entered in a valid format.  Finally, if all this has been done, send the save
     * request for the data, re-initialize all fields to empty and then re-send to the GUIUtility
     * to be initialized as fields requiring to be filled again.
     */
    private boolean isValidInfo() {

        //call the function that signals to the user that the field is empty and
        //is a required field.
        if (GUIUtility.signalEmptyField(
                this.getRequiredFields(),
                GUIUtility.DEFAULT_ERROR_FIELD_BORDER_COLOR,
                GUIUtility.DEFAULT_FIELD_BORDER_COLOR,
                messageLabel,
                GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR,
                GUIUtility.DEFAULT_ERROR_FIELD_TEXTFILL_COLOR,
                UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message)) {return false;}

        //validate that the email is in a valid email format
        if (!DataValidator.isValidEmail(emailTextField.getText())) {
            GUIUtility.displayFieldError(emailTextField,messageLabel,
                    UserMessages.ErrorMessages.INVALID_EMAIL_MESSAGE.message);
            return false;
        }
        // if the function gets here, then all the contact info is valid so process the contact
        //details and display the submit message to the user.
       save();
        //clear the fields to receive new values and re-initialize required fields for validation
        //purposes.
        GUIUtility.clearAllInputTextFields((Pane)emailTextField.getParent());
        initFieldValidation();
        displaySubmitMessage();
        return true;
    }

    /**
     * Save creates a new ContactObservableData object required to pass the contact email information
     * to the model layer, so that it, in turn can pass the insertion request to the database
     * layer.  The method passes on the information to the model layer via getOperation().postData().
     * Any exceptions that are generated are handled by the LogError class.
     */
    private void save() {

        Contact contactData = new ContactObservableData(
                0,
                nameTextField.getText().trim(),
                messageTextArea.getText().trim(),
                emailTextField.getText().trim(),
                null);
        try {
            //this will generate a UserAlreadyExistsException if the username already
            //exists in the database.  If no error is generated, then the postData
            //was successful, so we can show a success message to the user.
            getOperation().postData(contactData);
        } catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);
        } catch (Exception e) {
            LogError.logUnrecoverableError(e);
        }
    }

    /**
     * // InitFieldValidation initializes it's list of required fields with the InputTextType Controls
     * that are it's fields that require the user to fill them.  It then passes the list on to
     * the GUIUtility class function signalEmptyField for the method to change the appearance of the controls
     * so the user knows that these are required fields.
     */
    public void initFieldValidation() {

        getRequiredFields().clear();

        //add the forms required fields
        getRequiredFields().add(nameTextField);
        getRequiredFields().add(emailTextField);

        //call the function that signals to the user that the field is empty and
        //is a required field.  Do this at program start and after the operation that needed
        // validation is completed so the user gets the feedback right away.
        GUIUtility.signalEmptyField(
                this.getRequiredFields(),
                GUIUtility.DEFAULT_ERROR_FIELD_BORDER_COLOR,
                GUIUtility.DEFAULT_FIELD_BORDER_COLOR,
                messageLabel,
                GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR,
                GUIUtility.DEFAULT_ERROR_FIELD_TEXTFILL_COLOR,
                UserMessages.ErrorMessages.BLANK_ERROR_MESSAGE.message);


    }

    /**
     *  displaySubmitMessage() simply displays a message to the user that their email was submitted and
     *  will be treated within a certain given delay of time.  It also makes sure the message text of the
     *  label used to display the user messages is set to default text color and not red.
     */
    private void displaySubmitMessage() {
        messageLabel.setText(UserMessages.Messages.SUBMIT_MESSAGE.message);
        messageLabel.setStyle("-fx-text-fill: " + GUIUtility.DEFAULT_FIELD_TEXTFILL_COLOR + ";");
        messageLabel.setVisible(true);

    }
}
