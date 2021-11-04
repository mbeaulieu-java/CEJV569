package cejv569.medicationtracker.exceptions;


/**
 * WrongPassword Exception is a custom exception used by the application during login, if the
 * password used by the user does not match the one in the database for that user.
 * The exception is thus thrown and the calling client then shows the appropriate error message to the user.
 */
public class WrongPasswordException extends Exception {
    private String message;

    public WrongPasswordException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
