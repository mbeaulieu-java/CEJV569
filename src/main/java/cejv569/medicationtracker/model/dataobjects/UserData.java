package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.User;

/**
 *  UserData is a subclass of DBData and implements the data interface User. It is used to
 *  process user data between the model layer
 *  and the database layer.
 */

public class UserData extends DBData implements User {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String telephone;

    public UserData(int id, String firstName, String lastName, String userName,
                    String password, String email, String telephone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    //setters and getters

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getFirstName() {
        return firstName;
    }
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Override
    public String getLastName() {
        return lastName;
    }
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getTelephone() {
        return telephone;
    }
    @Override
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
