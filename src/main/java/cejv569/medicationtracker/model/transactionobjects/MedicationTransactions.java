package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.model.transactioninterfaces.MedicationTransaction;

public class MedicationTransactions extends DataTransactions implements MedicationTransaction {
    public MedicationTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }
}
