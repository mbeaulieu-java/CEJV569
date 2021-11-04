package cejv569.medicationtracker.view.viewdata;

import cejv569.medicationtracker.model.datainterfaces.Ingredient;

public class IngredientObservableData extends ViewObservableData implements Ingredient {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getCommonName() {
        return null;
    }

    @Override
    public void setCommonName(String commonName) {

    }

    @Override
    public String getScientificName() {
        return null;
    }

    @Override
    public void setScientificName(String scientificName) {

    }

    @Override
    public boolean isMedicinal() {
        return false;
    }

    @Override
    public void setMedicinal(boolean medicinal) {

    }
}
