package cejv569.medicationtracker.model.dataobjects;

import java.sql.Timestamp;

public class EffectsHistoryData extends DBData {

    private int id;
    private int userMedId;
    private int effectId;
    private String severity;
    private String duration;
    private Timestamp effectDate;

    //Constructor
    public EffectsHistoryData(int id, int userMedId, int effectId, String severity, String duration, Timestamp effectDate) {
        this.id = id;
        this.userMedId = userMedId;
        this.effectId = effectId;
        this.severity = severity;
        this.duration = duration;
        this.effectDate = effectDate;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserMedId() {
        return userMedId;
    }

    public void setUserMedId(int userMedId) {
        this.userMedId = userMedId;
    }

    public int getEffectId() {
        return effectId;
    }

    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Timestamp getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Timestamp effectDate) {
        this.effectDate = effectDate;
    }
}
