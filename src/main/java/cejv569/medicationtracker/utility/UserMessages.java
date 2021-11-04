package cejv569.medicationtracker.utility;

/**
 * Is a class containing enums for all the types of messages that need to be displayed to the user.
 */
public class UserMessages {

    /**
     * Enum class which encapsulates all the error message strings that the app would need to
     * display to the user.
     */
    public enum ErrorMessages {

        INVALID_PASSWORD_MESSAGE("Invalid password. " +
                "The password should be at least 8 characters long."),
        INVALID_EMAIL_MESSAGE("Invalid email address. The value must not" +
                " be empty and must contain the following symbols: \' @ . \'"),
        BLANK_ERROR_MESSAGE("Field cannot be blank."),
        PASSWORD_NOMATCH_ERROR_MESSAGE("The password does not match the password confirmation value."),
        UNEXPECTED_ERROR_HEADING("Unexpected Error"),
        UNEXPECTED_ERROR_MESSAGE("An unexpected error has occured forcing the application to close."),
        WRONG_USERNAME_ERROR_MESSAGE("The user name entered was not found in the database. Please" +
                " try again or sign up via the Sign Up icon below, then try logging in again."),
        WRONG_PASSWORD_ERROR_MESSAGE("The password entered does not match the one registered in the " +
                " database for this user name."),
        USERNAME_ALREADY_EXISTS_MESSAGE("This user name already exists in our system.  " +
                "Please try logging into the "
                + "system again with this user name or signup with a new user name value.");

        ErrorMessages(String msg) {
            this.message = msg;
        }

        public String message;
    }

    /**
     *  Enum class which encapsulates all the regular message strings that the app would need to
     *  display to the user.
     */
    public enum Messages {

            SUBMIT_MESSAGE("Your message has been sent. You should receive a response " +
                    " in the next 24 hours. "),
            SUBMIT_TITLE ("Message Submitted"),
            SAVED_MESSAGE("The information has been saved successfully to the database."),
            SAVED_TITLE ("Information Saved");

            Messages(String msg) {this.message = msg;}
            public String message;
        }
}
