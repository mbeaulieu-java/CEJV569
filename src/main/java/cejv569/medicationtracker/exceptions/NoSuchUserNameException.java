package cejv569.medicationtracker.exceptions;

/**
 * NoSuchUserNameException is a custom exception used by the application during login, if the
 * user name used by the user does not exist in the database.  The exception is thus
 * thrown and the calling client then shows the appropriate error message to the user.
 */
public class NoSuchUserNameException extends Exception{
    private String message;

    public NoSuchUserNameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
