package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.model.transactioninterfaces.EffectHistoryTransaction;

public class EffectHistoryTransactions extends DataTransactions implements EffectHistoryTransaction {

    public EffectHistoryTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }
}
