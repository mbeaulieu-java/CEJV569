package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;
import cejv569.medicationtracker.model.transactioninterfaces.UserMedicationTransaction;

public class UserMedicationTransactions extends DataTransactions implements UserMedicationTransaction {
    public UserMedicationTransactions(MedTrackDatasource datasource) {
        super(datasource);
    }
}
