package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.model.transactioninterfaces.EffectTransaction;

public class EffectTransactions extends DataTransactions implements EffectTransaction {

    public EffectTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }
}
