package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.EffectHistory;

import java.sql.Timestamp;

public class EffectHistoryObservableData extends ViewObservableData implements EffectHistory {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public int getUserMedId() {
        return 0;
    }

    @Override
    public void setUserMedId(int userMedId) {

    }

    @Override
    public int getEffectId() {
        return 0;
    }

    @Override
    public void setEffectId(int effectId) {

    }

    @Override
    public String getSeverity() {
        return null;
    }

    @Override
    public void setSeverity(String severity) {

    }

    @Override
    public String getDuration() {
        return null;
    }

    @Override
    public void setDuration(String duration) {

    }

    @Override
    public Timestamp getEffectDate() {
        return null;
    }

    @Override
    public void setEffectDate(Timestamp effectDate) {

    }
}
