package cejv569.medicationtracker.exceptions;

/**
 * UserAlreadyExistsException is a custom exception.  It is used in the Sign Up processing
 * to make sure that each user record inserted into the database has a unique user name.
 * Hence, this exception will be thrown if an attempt is made to add a new user, with an already existing
 * user name, into the database.
 */

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
