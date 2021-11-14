module cejv569.medicationtracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires javax.resource.api;
    requires java.naming;

    //view parent package
    opens cejv569.medicationtracker.view.viewcontrollers to javafx.fxml;
    exports cejv569.medicationtracker.view.viewcontrollers;
    opens cejv569.medicationtracker.view.viewdata to javafx.fxml;
    exports cejv569.medicationtracker.view.viewdata;

    opens cejv569.medicationtracker.view.customcellclasses to javafx.fxml;
    exports cejv569.medicationtracker.view.customcellclasses;

    opens cejv569.medicationtracker.database to javafx.fxml;
    exports cejv569.medicationtracker.database;



    opens cejv569.medicationtracker.utility to javafx.fxml;
    exports cejv569.medicationtracker.utility;

    opens cejv569.medicationtracker to javafx.fxml;
    exports cejv569.medicationtracker;
    exports cejv569.medicationtracker.events;
    opens cejv569.medicationtracker.events to javafx.fxml;

    //model parent package
    opens cejv569.medicationtracker.model.operationobjects to javafx.fxml;
    exports cejv569.medicationtracker.model.operationobjects;

    opens cejv569.medicationtracker.model.dataobjects to javafx.fxml;
    exports cejv569.medicationtracker.model.dataobjects;

    opens cejv569.medicationtracker.model.operationinterfaces to javafx.fxml;
    exports cejv569.medicationtracker.model.operationinterfaces;

    opens cejv569.medicationtracker.model.transactioninterfaces to javafx.fxml;
    exports cejv569.medicationtracker.model.transactioninterfaces;

    opens cejv569.medicationtracker.model.transactionobjects to javafx.fxml;
    exports cejv569.medicationtracker.model.transactionobjects;

    opens cejv569.medicationtracker.model.datainterfaces to javafx.fxml;
    exports cejv569.medicationtracker.model.datainterfaces;


}