package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationHistoryTransaction;

public class MedicationHistoryTransactions extends DataTransactions implements MedicationHistoryTransaction {
    public MedicationHistoryTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }
}
