package cejv569.medicationtracker.model.dataobjects;

import cejv569.medicationtracker.model.datainterfaces.EffectHistory;

import java.sql.Timestamp;

public class EffectHistoryData extends DBData implements EffectHistory {

    private int id;
    private int userMedId;
    private int effectId;
    private String severity;
    private String duration;
    private Timestamp effectDate;

    //Constructor
    public EffectHistoryData(int id, int userMedId, int effectId, String severity, String duration, Timestamp effectDate) {
        this.id = id;
        this.userMedId = userMedId;
        this.effectId = effectId;
        this.severity = severity;
        this.duration = duration;
        this.effectDate = effectDate;
    }

    //Getters and Setters

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int getUserMedId() {
        return userMedId;
    }
    @Override
    public void setUserMedId(int userMedId) {
        this.userMedId = userMedId;
    }
    @Override
    public int getEffectId() {
        return effectId;
    }
    @Override
    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }
    @Override
    public String getSeverity() {
        return severity;
    }
    @Override
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    @Override
    public String getDuration() {
        return duration;
    }
    @Override
    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Override
    public Timestamp getEffectDate() {
        return effectDate;
    }
    @Override
    public void setEffectDate(Timestamp effectDate) {
        this.effectDate = effectDate;
    }
}
