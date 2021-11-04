package cejv569.medicationtracker.model.transactionobjects;

import cejv569.medicationtracker.database.MedTrackDatasource;

/**
 * DataTransactions is the base class for all the transactions subclasses which perform the actual
 * database operations by executing the various prepared statements containing the sql statements
 * retrieved from the .sql files.  These subclasses are part of the database layer.
 */
public class DataTransactions {
    private MedTrackDatasource datasource;

    //Constructor, receives a MedTrackDatasource instance which contains the MySqlDatasource
    //with the connection to the database.
    public DataTransactions(MedTrackDatasource datasource) {
        this.datasource = datasource;
    }

    //Getters and Setters
    public MedTrackDatasource getDatasource() {
        return datasource;
    }

    public void setDatasource(MedTrackDatasource datasource) {
        this.datasource = datasource;
    }
}
