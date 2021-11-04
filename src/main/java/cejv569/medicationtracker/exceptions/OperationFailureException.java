package cejv569.medicationtracker.exceptions;

/**
 * OperationFailureException is a custom exception which the application throws if a runtime error
 * occurs or a logical error occurs, from which or because of which, the application cannot
 * continue operating in it's current purpose.  The application cannot recover from it and is
 * forced to shut down.  The exception would be caught and handled by showing the user an
 * appropriate message and the application would be exited.
 */
public class OperationFailureException extends Exception{
    private String message;
    public OperationFailureException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

}
