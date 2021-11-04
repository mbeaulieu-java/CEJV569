package cejv569.medicationtracker;

import cejv569.medicationtracker.database.MedTrackDatasource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *  APPLICATION STRUCTURE OVERVIEW
 *
 *  Note: For specific information about the functionality of the MedTrack app see the Application
 *  Description file in the resources package.
 *
 *  The application is divided into essentially 4 layers:
 *      1) The view layer : contains all the fxml forms and their associated controller classes.
 *                          All view controller classes have the ViewController class as a parent.
 *                          (viewcontrollers package)
 *
 *      2) The model layer:  This includes controllers that take data from the view layer, apply app
 *      business rules to it and then transfer the data to the database layer.  It also transforms
 *      data received from the database layer to a whatever format required by the view layer.
 *      All controllers of the model layer have DataController as a parent class. (model package - controller package)
 *
 *      3) This is the model/data layer: It also has controllers which actually are responsible for
 *      initializing and executing the queries required to store, update, delete and retrieve
 *      data from the database.  All these controllers have DataTransactions as a parent class.
 *      (transactionobjects package).
 *
 *      4) The database layer - It includes the MedTrackDatasource class which represents
 *  *      the database connection(s).  (database package).
 *
 *      Each of the first 3 layers communicate with each other via interfaces.
 *
 *      A) Communications between the view layer and model layer are mediated by the
 *      Operation interfaces who are sub classes of the ViewOperation interface.
 *
 *      i) The ViewOperation Interfaces are used by the ViewControllers and are implemented by
 *      the DataController subclasses. (operationinterfaces package & controllers package)
 *
 *
 *      B) Communication between the model layer and the data layer is mediated by the Transaction
 *      interfaces, which are subclasses of the class DataTransaction.
 *
 *      i) The Transaction Interfaces are used by the DataController children and are implemented by
 *  *      the DataTransactions subclasses. (transactioninterfaces and transactionobjects packages).
 *
 *      C) The DataTransactions subclasses work directly with the prepared statements returned by the
 *      MedTrackDatasource.
 *
 *      Data is transmitted via two different types of data objects.
 *
 *          A) View Data - all sub classes of the class ViewObservableData.  This comprises all data
 *          retrieved from the user via the gui, which needs to be transmitted to the model layer.
 *          These objects possess observable properties.  View Data gets transmitted between
 *          the view and model layers. (viewdata package)
 *
 *
 *          B) Data Objects - all sub classes of DBData.  These are simple java classes
 *          which mirror the fields to be found in the resultsets produced by the databases'
 *          queries.  DBData objects type objects are exchanged between the model and data layers.
 *          (dataobjects package)
 *
 *          So, essentially requests are sent through the view layer to the model layer and
 *          sometimes on to the data layer when information must be retrieved or a CRUD
 *          operation must be performed in the database.
 *
 *          Further classes have been created to provide support to the operations performed
 *          by the app:
 *
 *          Controller classes:  ApplicationController - the "master" controller for the app
 *
 *          Utility classes: DataValidator, GUIUtility, LogError and UserMessages (utility package)
 *
 *          Custom Exception classes:NoSuchUserNameexception, OperationFailureException, UserAlreadyExistsException &
 *          WrongPasswordException (exceptions package)
 *
 *          Events - for now no classes in this package are in use.
 *
 *          Database :
 *              1) The MedTackDataSource - encapsulates a MySqlDatasource object to create and manage
 *              database connections.  It also acts as a data "service" provider by retrieving
 *              the required query to be executed via an SQL Transactions key.
 *
 *              2) ResultSetColumnNamesByTransactionKey :  This class contains
 *              enum classes which represent queries in the database from which information
 *              must be extracted by field/column name.  It thus maps constants to the actual column names
 *              used in the queries.
 *
 *              3) SQLPropertiesTransactionKeys : This class maps the key-value mappings to be
 *              found in the transactionsqlmatch.properties file to constants the app can use.
 *              This essentially is key words used to denote the type of transaction the query
 *              represents and maps it to the physical address of the sql statement - a .sql
 *              file located in the ./resources/sql directory.
 *
 *              4)medtrackdbsettings.properties - is a file used to store the initialization
 *              parameters for the database.
 */







public class MedTrack extends Application {

    private final String LOGIN_FILE_PATH = "loginForm.fxml";

    /**
     * Start() initializes the datasource property of the ApplicationController class with
     * an instance of a MedTrack database connection by passing it an instance of the
     * MedTrackDatasource container class.  It also displays the login form.
     * @param stage, Stage type argument which is the main form of the application
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        //This will be implemented later to catch a custom error event
//        EventType<UnexpectedErrorEvent> unexpectedErrorEventType = new EventType<UnexpectedErrorEvent>();
//        EventHandler<UnexpectedErrorEvent> unexpectedErrorHandler = new EventHandler<UnexpectedErrorEvent>() {
//            @Override
//            public void handle(UnexpectedErrorEvent event) {
//                if (!event.isConsumed()) {
//                    event.consume();
//                }
//                exitApplication(stage);
//            }
//        };

        //set the Database datasource for the app via a MedTrackDataSource instance which
        //instantiates a database connection with the MedTrack db.
            ApplicationController.getInstance().setDatasource(MedTrackDatasource.getinstance());

        //load and display the log in form.
        FXMLLoader fxmlLoader = new FXMLLoader(MedTrack.class.getResource(this.LOGIN_FILE_PATH));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.addEventHandler(unexpectedErrorEventType,unexpectedErrorHandler);
        stage.setTitle("MedTrack");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * stop() - The stop() method of the Application parent class is overriden to add some clean up
     * code which closes the database connection via the encapsulating instance MedTrackDatasource.
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        MedTrackDatasource.getinstance().closeConnection();
        super.stop();

    }

    /**x
     * Application entry point
     * @param args
     */
    public static void main(String[] args) {
        launch();}
}