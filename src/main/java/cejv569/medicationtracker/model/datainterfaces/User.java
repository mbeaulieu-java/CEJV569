package cejv569.medicationtracker.model.datainterfaces;

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
