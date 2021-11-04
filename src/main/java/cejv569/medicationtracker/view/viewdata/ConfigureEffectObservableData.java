package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.Effect;

public class ConfigureEffectObservableData extends ViewObservableData implements Effect {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public void setLabel(String label) {

    }

    @Override
    public String getEffectDescription() {
        return null;
    }

    @Override
    public void setEffectDescription(String effectDescription) {

    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public void setNegative(boolean negative) {

    }

    @Override
    public String getBodyLocation() {
        return null;
    }

    @Override
    public void setBodyLocation(String bodyLocation) {

    }
}
