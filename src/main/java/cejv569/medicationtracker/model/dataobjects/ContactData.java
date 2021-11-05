package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.Contact;

import java.sql.Timestamp;

/**
 *  ContactData is a subclass of DBData and implements the interface Contact.
 *  It is a data structure used to process information from email communications from users or
 *  people who are not users
 *  but sent a communication to the company (which is completely fictional with a name that
 *  is hopefully not trademarked).  This object is used to transfer the data between the
 *  model layer and the database layer.
 */

public class ContactData extends DBData implements Contact {
    private int id;
    private String fullName; //senders full name
    private String message; //content of the email
    private Timestamp date; // timestamp of when the email was sent
    private String email; // the email address of the user

    public ContactData(int id, String fullName,
                       String message, Timestamp date, String email) {
        this.id = id;
        this.fullName = fullName;
        this.message = message;
        this.date = date;
        this.email = email;
    }

    //Setters and Getters

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getFullName() {
        return fullName;
    }
    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public Timestamp getDate() {
        return date;
    }
    @Override
    public void setDate(Timestamp date) {
        this.date = date;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
