package cejv569.medicationtracker.database;

/**
 * This class contains an enumeration QLTransactionKeys which represent constants that can be
 * used within the app to retrieve SQL queries files from with the database package classes.
 * Principally from the MedTrackDataSource class. Each key value corresponds to a key
 * in the transactionsqlmatch.properties file.  This properties file contains the directory path
 * and the key-value pairs correspoding to the keys below and the related filename for each
 * .sql file in the ./resources/sql/ directory.  In the case of this project, the sql files are
 * locally stored, but in theory they could be stored on another server.  The app can use the
 * queries without needing tp know the specific .sql file name of the query nor it's specific
 * location.  It just needs to use the constants provided in the SQLTransactionKeys enum.
 */

public class SQLPropertiesTransactionKeys {
    private SQLPropertiesTransactionKeys() {}

    public enum SQLTransactionKeys {
        SQL_DIRECTORY("sql_file_path"),
        CREATE_CONTACT("create_contact"),
        READ_USER_INFO ("read_user_info"),
        VALIDATE_USERNAME ("validate_username"),
        CREATE_USER_INFO("create_user"),
        UPDATE_USER_INFO("update_user"),
        READ_INGREDIENTS_INFO("read_ingredients"),
        READ_MEDICATION_INGREDIENTS_INFO("read_medication_ingredients"),
        READ_FORMATS_INFO("read_formats_info"),
        READ_MEASUREMENT_UNITS_INFO("read_measurement_units_info"),
        READ_MEDICATIONS_INFO("read_medications_info"),
        CREATE_MEDICATION_INGREDIENTS_INFO("create_medication_ingredients"),
        DELETE_MEDICATION_INGREDIENTS_INFO("delete_medication_ingredients"),
        CREATE_MEDICATION_INFO("create_medication"),
        UPDATE_MEDICATION_INFO("update_medication"),
        VERIFY_UNIQUE_MEDICATION("verify_unique_medication");

        SQLTransactionKeys(String tKey) {
            this.tKey = tKey;
        }

        public String tKey;
    }

}
