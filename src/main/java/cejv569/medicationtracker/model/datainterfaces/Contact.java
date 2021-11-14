package cejv569.medicationtracker.model.datainterfaces;

/**
 * The Contact interface is a child of the Data interface base class.  It reflects the
 * structure of the Contacts table in the database.  The interface is used to facilitate the
 * exchange of data between the ContactController (view layer), the ContactOperation
 * (model layer) and the ContactTransactions controller (data layer).
 */

import java.sql.Timestamp;

public interface Contact extends Data{

    //Setter and getters

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
