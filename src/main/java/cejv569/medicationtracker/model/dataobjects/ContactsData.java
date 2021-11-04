package cejv569.medicationtracker.model.dataobjects;

import java.sql.Timestamp;

/**
 *  ContactsData is a subclass of DBData and is a data structure used to process
 *  information from email communications from users or people who are not users
 *  but sent a communication to the company (which is completely fictional with a name that
 *  is hopefully not trademarked).  This object is used to transfer the data between the
 *  model layer and the database layer.
 */

public class ContactsData extends DBData {
    private int id;
    private String fullName; //senders full name
    private String message; //content of the email
    private Timestamp date; // timestamp of when the email was sent
    private String email; // the email address of the user

    public ContactsData(int id, String fullName,
                        String message, Timestamp date, String email) {
        this.id = id;
        this.fullName = fullName;
        this.message = message;
        this.date = date;
        this.email = email;
    }

    //Setters and Getters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
