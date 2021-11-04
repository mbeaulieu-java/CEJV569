package cejv569.medicationtracker.model.controllers;

import cejv569.medicationtracker.model.transactioninterfaces.DataTransaction;

/**
 * DataController is the base class for all the DataController sub classes in the program, those
 * contained in the controllers package.  The dataController classes represent the layer between
 * the views (GUI) and their related controllers, and the database layer.  The
 * data subclasses communicate with the ViewController subclasses via the ViewOperation child interfaces.
 * It is thus the middle layer of classes between the front-end and the back end (database) of the app.
 * Each DataController subclass must implement a ViewOperationInterface to process requests from
 * the ViewController subclasses.  They must also pass on those information requests to the
 * database layer (the DataTransactions subclasses) via the DataTransaction interface SubClasses.
 * To pass on those data requests to the database layer, each DataController subclass thus has
 * access to the next layer via it's transaction property/attribute (which is a DataTransaction child class).
 * Each DataController child class inherits this property from DataController.
 *  As the middle layer, the DataController child classes perform any data validation required
 *  on data received from the database or data received from the GUI layer,
 *  before a transaction is sent to the database.  This layer also transforms data objects into
 *  the right data type for either the GUI or Database layers.  The GUI layer requires ViewObservableData child
 *  classes that contain Observable type properties for use with controls.  Meanwhile, the database
 *  layer works with DBData objects that do not require observable properties and are instead
 *  plain java objects.
 *
 */
public abstract class DataController {

    protected DataTransaction transaction;

    public abstract void setTransaction(DataTransaction transaction);

}
