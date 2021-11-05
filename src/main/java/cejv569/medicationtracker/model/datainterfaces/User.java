package cejv569.medicationtracker.model.datainterfaces;

/**
 * The User interface is a child of the Data interface base class.  It reflects the
 * structure of the users table in the database.  The interface is used to facilitate the
 * exchange of data between the AccountController (view layer),
 * the LoginController (view layer),
 * the SignupController (view layer),
 * AccountDataController (model layer),
 * LoginDataController (model layer),
 * SignupDataController (model layer)
 * and the UserTransactions controller (data layer).
 */

public interface User extends Data{
    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getUserName();

    void setUserName(String userName);

    String getPassword();

    void setPassword(String password);

    String getEmail();

    void setEmail(String email);

    String getTelephone();

    void setTelephone(String telephone);
}
