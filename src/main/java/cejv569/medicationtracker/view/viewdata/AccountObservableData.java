package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.User;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;

/**
        *  AccountObservableData is a ViewObservableData subclass.  It is used to transfer user data back and forth
        * between the view layer and the model layer.
        *  This data object can be used by any of the view controller classes to transmit or display
        *  user data.
        *  is initialized with regular java data types but also contains Observable object
        *  properties to bind/initialize javafx controls.
        */

public class AccountObservableData extends ViewObservableData implements User {

    //properties
    private final static String ID_PROP_NAME = "id";
    private ReadOnlyIntegerWrapper id;

    private final static String FIRSTNAME_PROP_NAME = "firstName";
    private SimpleStringProperty firstName;

    private final static String LASTNAME_PROP_NAME = "lastName";
    private SimpleStringProperty lastName;

    private final static String USERNAME_PROP_NAME = "userName";
    private SimpleStringProperty userName;

    private final static String PASSWORD_PROP_NAME = "password";
    private SimpleStringProperty password;

    private final static String EMAIL_PROP_NAME = "email";
    private SimpleStringProperty email;

    private final static String TELEPHONE_PROP_NAME = "telephone";
    private SimpleStringProperty telephone;


    /**
     *  Constructor - initialized by the view controller classe with user account data to be displayed.
     *
     * @param id - int - primary key from the users table, used as a uesr id
     * @param firstName - String -  user's first name
     * @param lastName - String - user's last name
     * @param userName - String - user name, in the form of an email, used for login purposes.
     * @param password - String - password which must be at least 8 characters.  Is reqired for
     *                              login purposes.
     * @param email     - String - user's email address (may or may not be the same as the user name)
     * @param telephone - String - the user's telephone number
     */
    public AccountObservableData(int id, String firstName, String lastName,
                                 String userName, String password, String email,
                                 String telephone) {

        //create and initialize AccountObservableData values
        this.id = new ReadOnlyIntegerWrapper(this,ID_PROP_NAME,id);
        this.firstName = new SimpleStringProperty(this,FIRSTNAME_PROP_NAME,firstName);
        this.lastName = new SimpleStringProperty(this,LASTNAME_PROP_NAME,lastName);
        this.userName = new SimpleStringProperty(this,USERNAME_PROP_NAME,userName);
        this.password = new SimpleStringProperty(this,PASSWORD_PROP_NAME,password);
        this.email = new SimpleStringProperty(this,EMAIL_PROP_NAME,email);
        this.telephone = new SimpleStringProperty(this,TELEPHONE_PROP_NAME,telephone);
    }


    //getters and setters
    @Override
    public int getId() {
        return id.get();
    }

    public ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }
    @Override
    public void setId(int id) {
        this.id.set(id);
    }
    @Override
    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }
    @Override
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    @Override
    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }
    @Override
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    @Override
    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }
    @Override
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    @Override
    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password.set(password);
    }
    @Override
    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email.set(email);
    }
    @Override
    public String getTelephone() {
        return telephone.get();
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }
    @Override
    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }
}
