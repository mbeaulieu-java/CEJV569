package cejv569.medicationtracker.utility;

import javafx.scene.control.Alert;

/**
 * DataValidator is a simple static utility class which contains methods and functions to validate
 * various data entered by the user in different parts of the view layer in the app.
 */
public class DataValidator {

    //CONSTANTS

    //Regex String used to validate if the email follows the general format required of an email.
    //I don't use the RFC 5322 standard, https://emailregex.com/.  Just a very loose regular expression based
    //validation.

    public final static String EMAIL_REGEX =
            "^([\\w+ " +  "\\W N+]" + "{" + "1" + ",}" +
                    "@{" + "1" + "}" +
                    "[\\w+]{" + "1" + ",}" +
                    "\\.{" + "1" + "}" +
                    "[\\w+]{" + "1" + ",})";


    private DataValidator(){}


    /**
     *  isValidEmail() validates that the email format follow certain formatting rules such as using only certain
     *  character and not others, containing an @ followed by a domain name of the format .*.
     * @param email, String type - is the email address to be validated
     * @return - boolean type, returns true if the email matches the regex expression format and false if it
     * doesn't.
     */
    public static boolean isValidEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    /**
     * isValidUser() validates the user value entered, which is the user's email address.  Thus it firsts makes
     * sure the user field is not blank and then calls isValidEmail to verify that
     * the user parameter (email) has the proper format of an email.
     * @param user - String type - represents the user name which is to be validated, in the form of an email.
     * @return boolean type - returns true of the user field is not blank and the value entered  is formatted
     *                          as an email should be.
     */

    public static boolean isValidUser(String user) {
        user = user.trim();
        return !user.equals("") && isValidEmail(user);

    }

    /**
     * isValidPassword() validates that the user entered a password that is
     * at least 8 characters in length.  No other rules are applied.
     * @param password - String type, The password string to be validated.
     * @return - boolean type, returns true if the password is at least 8 characters in length and
     *                          false is it isn't.
     */
    public static boolean isValidPassword(String password) {
        password = password.trim();
        return (password.length() >= 8);
    }

    /**
     * displayError() displays an alert of type Error to the user, where the content of the alert dialog
     * is passed in as the errorMessage argument.
     * @param errorMessage - String type, represents the message to be displayed as the alert content.
     */
    public static void displayError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR,errorMessage);
        alert.show();
    }

}
