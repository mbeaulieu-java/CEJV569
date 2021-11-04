package cejv569.medicationtracker.model.dataobjects;

/**
 * Abstract class for data object subclasses.  These child classes are used to transfer data between the
 * middle (model) layer and the database layer.  They have no Observable properties but are plain
 * java objects.  Data input from queries (or tables) can be loaded into it's given data object
 * (child class of DBData) and transmitted from the database layer to the model layer.
 */
public abstract class DBData {
}
