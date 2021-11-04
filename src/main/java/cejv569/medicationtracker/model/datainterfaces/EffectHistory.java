package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Timestamp;

public interface EffectHistory{

    int getId();

    void setId(int id);

    int getUserMedId();

    void setUserMedId(int userMedId);

    int getEffectId();

    void setEffectId(int effectId);

    String getSeverity();

    void setSeverity(String severity);

    String getDuration();

    void setDuration(String duration);

    Timestamp getEffectDate();

    void setEffectDate(Timestamp effectDate);
}
