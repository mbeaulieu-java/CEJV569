package cejv569.medicationtracker.model.datainterfaces;

import java.sql.Timestamp;

/**
 * The EffectHistory interface is a child of the Data interface base class.  It reflects the
 * structure of the effect_history table in the database.  The interface is used to facilitate the
 * exchange of data between the EffectHistoryController(view layer), LogMedicationEffectController(view layer),
 * the EffectLogOperation(model layer), EffectHistoryOperation
 * (model layer) and the EffectHistoryTransactions controller (data layer).
 */

public interface EffectHistory extends Data{

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
