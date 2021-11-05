package cejv569.medicationtracker.model.datainterfaces;

/**
 * Data is an interface base class for the specialized child classes that support the different
 * data objects in the app.  There is thus a child interface for each of the data entities or
 * tables in the app.  Data subclasses are used to both limit the exchange of data between layers
 * to data objects that implement this interface and also to increase flexibility as well.  Each layer
 * is free to work with data objects that suit their own needs (sub as objects with observable properties)
 * but they can still be used for data exchange between layers, as long as they support the given
 * Data subclass.  This class could be used to add base functionality to data objects at a later
 * point.
 */
public interface Data {


}
