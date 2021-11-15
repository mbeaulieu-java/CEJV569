package cejv569.medicationtracker.database;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.utility.LogError;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * NOTE: DEPENDENCY on the SQLPropertiesTransactionKeys class.
 *
 * The MedTrackDataSource acts similarly to a SQL datasource in that it would be responsible
 * for a connection pool, where it manages the connections and reallocates them to
 * requesting clients when a given connection gets released.  In this basic case, there is no
 * connection pool, just one connection.  The class is a Singleton class, as there can only be one
 * datasource in the application at a time.
 *
 * The class interacts very little with the actual tables and queries in the database.  It is mainly
 * responsible for managing the establishment of a connection, sharing the connection and closing it.
 * It also provides a mechanism by which to retrieve specific queries from the database (or their .sql files)
 * as prepared statement objects.  The client which requests the prepared Statement object from
 * the MedTrackDataSource, is then  responsible for initializing the queries parameters and
 * executing the query.  The MedTrackDataSource only provides the service of retrieving using the
 * SQL Transaction Keys from SQLPropertiesTransactionKeys and initializing the prepared Statement object.
 */

public class MedTrackDatasource {
    //CONSTANTS

    //Path to the properties file containing all the database initialization parameters such as
    //host, user name etc.
    private final String MEDTRACKDB_SETTINGS_FILE_PATH =
            "src/main/resources/cejv569/medicationtracker/properties/medtrackdbsettings.properties";

    // Path to the properties file containing Transaction KEY To Query filename.sql pair matchings.
    private final String TRANSACTION_SQL_MATCH_FILE_PATH =
            "src/main/resources/cejv569/medicationtracker/properties/transactionsqlmatch.properties";

    private final String DEFAULT_PRIMARY_KEY_NAME = "key";
    private  MysqlDataSource dataSource;
    private  Connection connection;
    private Properties sqlTransactionProperties;
    private static MedTrackDatasource instance;


    //Initializes the MysqlDatasource instance in the constructor
    private MedTrackDatasource() {
        initDataSource();
    }

    //SETTERS AND GETTERS

    /**
     *  Returns the singleton instance of the class
     * @return MedTrackDatasource type - singleton instance of the class.
     */
    public static MedTrackDatasource getinstance() {
        return (instance == null ? instance = new MedTrackDatasource() : instance);
    }

    /**
     *  getConnection, after validating that the dataSource was created, uses the mysqldatasource
     *  instance to retrieve a connection to the database and returns it to the client to be
     *  consumed.
     * @return a Connection type object representing the database connection.
     */
    public Connection getConnection() {
        try {
            if (!(dataSource == null)) {
                connection = dataSource.getConnection();
                if (connection.isValid(0)) {
                    return connection;
                }
            }
        } catch (SQLException e) {
            LogError.logUnrecoverableError(e);
        }
        return null;
    }

    /**
     * initDataSource reads the database initialization settings from the medtrackdbsettings.properties
     * file.  It then uses them to create and initialize an instance of MysqlDataSource.  This
     * instance, aptly named dataSource is used to manage the database connection.
     */
    private void initDataSource () {
        String host, port, database, user, password;
        connection = null;
        try
        {
            //read the database settings from the properties file into an InputStream
            InputStream inputStream = new FileInputStream(MEDTRACKDB_SETTINGS_FILE_PATH);

            //load the values into a Properties instance
            Properties properties = new Properties();
            properties.load(inputStream);
            host = properties.getProperty("host");
            port = properties.getProperty("port");
            database = properties.getProperty("databasename");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            //if all the values were retrieved then create a MysqlDataSource instance and
            //initialize it.
            if (!(
                    host == null || port == null ||
                            database == null || user == null ||
                            password == null))
            {
                dataSource = new MysqlDataSource();
                dataSource.setServerName(host);
                dataSource.setPortNumber(Integer.valueOf(port));
                dataSource.setDatabaseName(database);
                dataSource.setUser(user);
                dataSource.setPassword(password);

            } else { //generate a custom error if there is a problem initializing the database.
                throw new OperationFailureException("Missing database connection key " +
                        "values in " + MEDTRACKDB_SETTINGS_FILE_PATH);
            }
        } catch (OperationFailureException e) {
            LogError.logUnrecoverableError(e);

        } catch (SecurityException e) {
            LogError.logUnrecoverableError(e);

        }catch (FileNotFoundException e) {
            LogError.logUnrecoverableError(e);

        } catch (IOException e) {
            LogError.logUnrecoverableError(e);

        } catch (IllegalArgumentException e) {
            LogError.logUnrecoverableError(e);

        } catch (NullPointerException e) {
            LogError.logUnrecoverableError(e);
        }
    }

    /**
     * getSQLStatement is responsible for retrieving the corresponding query given a sqlTransactionKey.
     * It reads the sql statement from the .sql query's file and loads the sql statement
     * into a prepared statement instance which it returns to the requesting client.
     * @param sqlTransactionKey - the key used to identify the query, that is, the key matching
     *                          the physical .sql filename for the query.  This key-value match is
     *                          obtained in the transactionsqlmatch.properties file.
     * @return a preparedStatement instance, representing an instantiated statement that the client
     * can then initialize and execute.
     * @throws OperationFailureException - a custom exception representing
     * the occurence of some type of exception from which the application is not able to recover
     * from.  That is, the application cannot continue to operate its required behaviour.
     *
     */
    public PreparedStatement getSQLStatement(String sqlTransactionKey) throws OperationFailureException {
        String sqlDirectory;
        String sqlFileName;
        Path path;
        Stream<String> lines;
        String sqlString = null;
        PreparedStatement preparedStatement = null;

        try {
            //Load the contents of the transactionsqlmatch.properties file into a Properties
            //instance to retrieve the required .sql file based on it's SQLTransactionKey
            //key value correspondence.
            InputStream inputStream = new FileInputStream(TRANSACTION_SQL_MATCH_FILE_PATH);
            Properties properties = new Properties();
            properties.load(inputStream);
            sqlDirectory = properties.getProperty(SQLPropertiesTransactionKeys.SQLTransactionKeys.SQL_DIRECTORY.tKey);
            sqlFileName = properties.getProperty(sqlTransactionKey);

            //if the sql directory exists and the corresponding sql file was found,
            //read all the lines of text in the sql file in one shot.
            if (!(sqlDirectory == null || sqlFileName == null)) {
                path = Paths.get(sqlDirectory + sqlFileName);
                lines = Files.lines(path);

                //if there was data in the file, use the collect method of the stream to collate
                //all the lines into one String of sql.  In the case of this project, each
                //query represents only 1 Sql statement.
                if (lines != null) {
                    sqlString = lines.collect(Collectors.joining());
                }

                //if the sql statement is not empty, then create a preparedStatement instance
                //to be returned to the requesting class as a service to be consumed.
                if (!sqlString.equals("")) {
                    if (sqlString.toLowerCase().contains("insert")) {
                        preparedStatement = getConnection().prepareStatement(sqlString,
                                new String[] {this.DEFAULT_PRIMARY_KEY_NAME});//PREPAREDSTATEMENT.RETURN_GENERATED_KEYS
                    } else {
                        preparedStatement = getConnection().prepareStatement(sqlString);
                    }
                }
            }
        } catch (SecurityException e) {
            LogError.logUnrecoverableError(e);
        } catch (FileNotFoundException e) {
            LogError.logUnrecoverableError(e);
        } catch (IOException e) {
            LogError.logUnrecoverableError(e);
        } catch (IllegalArgumentException | NullPointerException e) {
            LogError.logUnrecoverableError(e);
        } catch (SQLException e) {
            LogError.logUnrecoverableError(e);
        } finally {
            //if a prepared statement could not be created to be returned generate an exception to
            // be caught by the calling client.
            if (preparedStatement == null) {
                throw new OperationFailureException("The sql query with key: " + sqlTransactionKey +
                        " was not found");
            }
            //return the preparedStatement to the requesting class instance
            return preparedStatement;
        }
    }

        public String getSQLQuery(String sqlTransactionKey) throws OperationFailureException{
            String sqlDirectory;
            String sqlFileName;
            Path path;
            Stream<String> lines;
            String sqlString = null;


            try {
                //Load the contents of the transactionsqlmatch.properties file into a Properties
                //instance to retrieve the required .sql file based on it's SQLTransactionKey
                //key value correspondence.
                InputStream inputStream = new FileInputStream(TRANSACTION_SQL_MATCH_FILE_PATH);
                Properties properties = new Properties();
                properties.load(inputStream);
                sqlDirectory = properties.getProperty(SQLPropertiesTransactionKeys.SQLTransactionKeys.SQL_DIRECTORY.tKey);
                sqlFileName = properties.getProperty(sqlTransactionKey);

                //if the sql directory exists and the corresponding sql file was found,
                //read all the lines of text in the sql file in one shot.
                if (!(sqlDirectory == null || sqlFileName == null)) {
                    path = Paths.get(sqlDirectory + sqlFileName);
                    lines = Files.lines(path);

                    //if there was data in the file, use the collect method of the stream to collate
                    //all the lines into one String of sql.  In the case of this project, each
                    //query represents only 1 Sql statement.
                    if (lines != null) {
                        sqlString = lines.collect(Collectors.joining());
                    }

                    //if the sql statement is not empty, then create a preparedStatement instance
                    //to be returned to the requesting class as a service to be consumed.
                    if (sqlString.equals("")) {
                       throw new IOException("SQl File is empty. " + sqlTransactionKey);
                    }
                }
            } catch (SecurityException e) {
                LogError.logUnrecoverableError(e);
            } catch (FileNotFoundException e) {
                LogError.logUnrecoverableError(e);
            } catch(IOException e){
                LogError.logUnrecoverableError(e);
            } catch (IllegalArgumentException | NullPointerException e) {
                LogError.logUnrecoverableError(e);
            }
            //return the query or return null if an error occured
            return (sqlString.equals("") ? null : sqlString);
        }


    /**
     *  closeConnection is responsible for closing the database connection.  It validates
     *  first to make sure the connection is not closed already, commits any unsaved data and
     *  closes the connection.
     */
   public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        } catch (SQLException e) {
            LogError.logUnrecoverableError(e);
        }
   }



}
