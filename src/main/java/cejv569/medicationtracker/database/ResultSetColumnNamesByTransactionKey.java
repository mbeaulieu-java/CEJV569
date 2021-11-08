package cejv569.medicationtracker.database;

/**
 *   Note:  *** THIS CLASS DEPENDS ON THE CLASS SQLPropertiesTransactionKeys ***
 *
 *   ResultSetColumnNamesByTransactionKey is a singleton class that contains a mapping between a
 *   constant key and the actual field/column name of fields contained in each query used by
 *   the application.  Each enum represents a query or query data resultset. Within each of
 *   these enums, are the column names for that particular query.  The names of each enum
 *   are not mapped to the actual filename.sql name.  As the app does not know these.
 *   Instead, we use the constants from the SQLPropertiesTransactionKeys class to map the query
 *   key to the column fields for that query.
 *
 *   Also note: Only those queries from which values must be retrieved by column name have a mapping.
 *   Queries where values are only inserted or deleted from the database are not included,
 *   as there is no need to refer to the specific column names directly.  This is mainly for
 *   SELECT queries where specific values are retrieved to be displayed.
 */

public class ResultSetColumnNamesByTransactionKey {

    private static ResultSetColumnNamesByTransactionKey instance;
    private ResultSetColumnNamesByTransactionKey() {}

    /**
     *  Retrieves the singleton instance of this class
     * @return ResultSetColumnNamesByTransactionKey type - representing the instantiated Singleton insance
     * of this class.
     */
    public static ResultSetColumnNamesByTransactionKey getInstance() {
        return (instance == null ? instance = new ResultSetColumnNamesByTransactionKey() : instance);
    }

    /**
     * Column field names for the constant VALIDATE_USERNAME key in the SQLPropertiesTransactionKeys class.
     * The query retrieves the id, user_name and user_password based on the user_name parameter provided.
     */
    public enum Validate_UserName {
        ID ("id"),
        USER_NAME ("user_name"),
        USER_PASSWORD ("user_password");

        Validate_UserName(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }

    /**
     * Column field names for the constant READ_USER_INFO key in the SQLPropertiesTransactionKeys class.
     * The query retrieves the users table fields based on the id ID field paremeter provided to
     * the query.
     * */
    public enum User_Info {

        ID ("id"),
        FIRST_NAME ("first_name"),
        LAST_NAME("last_name"),
        USER_NAME ("user_name"),
        USER_PASSWORD ("user_password"),
        EMAIL("email"),
        TELEPHONE("telephone");

        User_Info(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }

    public enum Ingredients_Info {

        ID("id"),
        NAME("name"),
        MEDICINAL("medicinal");

        Ingredients_Info(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }

    public enum Medication_Ingredients_Info {

        ID("id"),
        MEDICATION_ID("med_id"),
        INGREDIENT_ID("ingredient_id"),
        NAME("name");

        Medication_Ingredients_Info(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }

    public enum Formats_Info {

        ID("id"),
        LABEL("label");

        Formats_Info(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }

    public enum Measurement_Units_Info {

        ID("id"),
        UNIT_NAME("unit_name");

        Measurement_Units_Info(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }

    public enum Medications_Info {

        ID("id"),
        FORMAT_ID("format_id"),
        MEASUREMENT_ID("measurement_id"),
        USER_ID("userid"),
        BRAND_NAME("brand_name"),
        GENERIC_NAME("generic_name");

        Medications_Info(String columnName) {
            this.columnName = columnName;
        }

        public String columnName;
    }
}
