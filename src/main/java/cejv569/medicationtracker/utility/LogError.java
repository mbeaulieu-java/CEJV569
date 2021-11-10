package cejv569.medicationtracker.utility;

import cejv569.medicationtracker.ApplicationController;
import cejv569.medicationtracker.exceptions.OperationFailureException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * LogError is a custom exception logger class.  Though a regular java logger class could have
 * been used, this class is used to process what the app considers errors that the app can recover
 * from or exceptions, logged as custom type OperationFailureExceptions, that the app can't recover
 * from.  Unrecoverable errors are those where either the database generates a runtime error or
 * certain required data can't be retrieved that is critical to the operation of the app.
 * If a OperationFailureException is logged, an error alert dialog is displayed to the user
 * advising them that an unexpected error has occurred forcing the app to shut down.
 */
public class LogError {

    public static Scene eventScene;

    //constructor
    private LogError() {
    }

    //not currently used but sets the Scene type attribute eventScene, to be able to set a custom
    // exception handler for a given Scene object.
    public static void setStage(Scene scene) {
        eventScene = scene;
    }

    /**
     * logRecoverableError is able to receive any exception type that extends the Exception class.
     * For now, it doesn't perform anything other than print the stack trace for the exception
     * to the console (for now).
     * @param e Exception Type - exception generated somewhere in the app.
     */
    public static void logRecoverableError(Exception e) {
        e.printStackTrace();
    }

    /**
     *  logUnrecoverableError is used to log errors that the app can't recover from such as a
     *  runtime exception or in the case where certain data can't be retrieved that is required by
     *  the app to function properly.  These exceptions are caught elsewhere in the app and
     *  the app throws a custom exception, OperationFailureException, which will be handled by the app in a custom
     *  way.  In this case, an alert error dialog is displayed with the message that
     *  the application will need to shut down to an unexpected error.
     *  It then loops through any open windows (isShowing == true) and assigns their OnCloseRequestEventDispatcher
     *  to call ApplicationController...doUnexpectedErrorOnCloseCleanUp() to do any required
     *  cleanup processes required before the app shuts down.  After which, if the window is
     *  a Stage type instance, it calls it's close method, which in effect will call the
     *  doUnexpectedErrorOnCloseCleanup() eventhandler.
     *  It then sets a timer for 2 seconds to let the app time to shut down before system.exit() is called to shut
     *  down the app.
     * @param e - Exception type - can receive any Exception type object that
     *          extends Exception.
     */
    public static void logUnrecoverableError (Exception e) {
//
//        EventType<UnexpectedErrorEvent> unexpectedErrorEventType = new EventType<UnexpectedErrorEvent>();
//        EventDispatcher dispatcher=

        //if the exception is an unrecoverable type exception...
        if (e instanceof OperationFailureException) {
            //show a message warning the user that the app must shut down
            Alert  alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(UserMessages.ErrorMessages.UNEXPECTED_ERROR_HEADING.message);
            alert.setContentText(UserMessages.ErrorMessages.UNEXPECTED_ERROR_MESSAGE.message);
            alert.show();

            //loop through any open windows and set an event handler to be called when the
            //window closes and then close the window if it's a stage.'
            List<Window> openWindows = (List<Window>) Stage.getWindows().stream().filter(Window::isShowing);
            for (Window o : openWindows) {
                //call an eventhandler that will perform cleanup code before the application exits.
                    o.setOnCloseRequest(ev->{
                    ApplicationController.getInstance().doUnexpectedErrorOnCloseCleanUp(); });

                    //if the window is a Stage instance, then close it.
                    if (o instanceof Stage) {
                    ((Stage) o).close();
                }
            }

            //Create a timertask to run the System.exit method, create a timer and schedule
            //the task to occur in a delay of 2000 milliseconds.
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.exit();
                }
            };
            Timer timer = new Timer("Timer");

            long delay = 2000L;
            timer.schedule(task, delay);
        }
    }
}
