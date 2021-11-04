package cejv569.medicationtracker;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.model.controllers.*;
import cejv569.medicationtracker.model.transactionobjects.*;
import cejv569.medicationtracker.view.viewcontrollers.*;

/**
 *  ApplicationController is the "master" controller for the app and is the controller
 *  responsible for initializing the class "communication chains" so the different application
 *  layers can communicate.  That is, the view layer with the model layer and the model layer with
 *  the database layer.  It does this by initializing each of the controllers with the interface it
 *  needs to use to communicate with the next layer in the chain.
 */
public class ApplicationController {

    private static ApplicationController instance;
    private MedTrackDatasource datasource;

    //Constructor
    private ApplicationController(){}

    /**
     * getInstance
     * @return - ApplicationController - returns a singleton instance of the class
     */
    public static ApplicationController getInstance(){
        return (instance == null ? instance = new ApplicationController(): instance );
    }

    //Getters and Setters


    public void setDatasource(MedTrackDatasource datasource) {
        this.datasource = datasource;
    }

    /**
     * operationFactory - receives an instance of each of the ViewController classes that
     * need to communicate to the model layer via ViewOperation interface subclasses.  The method
     * verifies the identity of the class and initializes each controller's ViewOperation property
     * with the class that implements it's corresponding viewoperation type child interface (this would be
     * a child class of DataController).
     * That is, each controller has a corresponding interface that it must use, that is a child
     * of ViewOperation class.  It thus creates and initializes the controller with the class
     * that implements that child interface.
     * @param controller ViewController -  a child class of ViewController that requires the initialization
     *                   of it's operation (ViewOperation) property to be able to transact
     *                   with classes in the model layer.
     */
    public void operationFactory(ViewController controller) {

        if (controller instanceof AccountController) {
            controller.setOperation(new AccountDataController());
        }
        if (controller instanceof ConfigureMedicationController) {
            controller.setOperation(new MedicationDataController());
        }
        if (controller instanceof EffectHistoryController) {
            controller.setOperation(new EffectHistoryDataController());
        }
        if (controller instanceof LoginController) {
            controller.setOperation(new LoginDataController());
        }
        if (controller instanceof LogMedicationController) {
            controller.setOperation(new MedLogDataController());
        }
        if (controller instanceof LogMedicationEffectController) {
            controller.setOperation(new EffectLogDataController());
        }
        if (controller instanceof LogMedicationPurchaseController) {
            controller.setOperation(new MedPurchaseDataController());
        }
        if (controller instanceof SignupController) {
            controller.setOperation(new SignupDataController());
        }
        if (controller instanceof ContactController) {
            controller.setOperation(new ContactDataController());
        }
    }


    /**
     * transactionFactory - receives an instance of each of the DataController classes(model layer
     * classes) that need to communicate with the database layer via
     * DataTransaction interface subclasses.  The method verifies the identity of the class and initializes each
     * controller's transaction (DataTransaction) interface property/atttribute with the class that implements
     * it's corresponding DataTransaction-type child interface.  The implementing class is a child class
     * of DataTransactions class.
     * Each controller has a corresponding interface that it must use, that is a child
     * of the DataTransaction class. The method thus creates and initializes the controller with the class
     * that implements that child interface.
     * @param controller DataController -  a child class of DataController that requires the initialization
     *                   of it's transaction (DataTransaction-type) property to be able to transact
     *                   with classes in the database layer.
     */
    public void transactionFactory(DataController controller) {

        if (controller instanceof AccountDataController) {
            controller.setTransaction(new UserTransactions(datasource));
        }
        if (controller instanceof MedicationDataController) {
            controller.setTransaction(new MedicationTransactions(datasource));
        }
        if (controller instanceof EffectHistoryDataController) {
            controller.setTransaction(new EffectHistoryTransactions(datasource));
        }
        if (controller instanceof LoginDataController) {
            controller.setTransaction(new UserTransactions(datasource));
        }
        if (controller instanceof MedLogDataController) {
            controller.setTransaction(new MedicationHistoryTransactions(datasource));
        }
        if (controller instanceof EffectLogDataController) {
            controller.setTransaction(new EffectHistoryTransactions(datasource));
        }
        if (controller instanceof MedPurchaseDataController) {
            controller.setTransaction(new UserMedicationTransactions(datasource));
        }
        if (controller instanceof SignupDataController) {
            controller.setTransaction(new UserTransactions(datasource));
        }
        if (controller instanceof ContactDataController) {
            controller.setTransaction(new ContactTransactions(datasource));
        }
    }

    /**
     *  doUnexpectedErrorOnCloseCleanUp method does any cleanup required in the case where
     *  the application must shut down do to an unexpected error from which it can't recover from.
     *  It closes the database connection and other processing.
     */
    public void  doUnexpectedErrorOnCloseCleanUp() {
        this.datasource.closeConnection();
    }
}
