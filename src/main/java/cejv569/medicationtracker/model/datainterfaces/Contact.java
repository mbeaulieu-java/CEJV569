package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Timestamp;

public interface Contact {
    int getId();

    void setId(int id);

    String getFullName();

    void setFullName(String fullName);

    String getMessage();

    void setMessage(String message);

    Timestamp getDate();

    void setDate(Timestamp date);

    String getEmail();

    void setEmail(String email);
}
