package cejv569.medicationtracker.view.viewcontrollers;

import cejv569.medicationtracker.model.operationinterfaces.ViewOperation;
import javafx.scene.control.TextInputControl;

import java.util.ArrayList;
import java.util.List;

/**
 * The ViewController class is an abstract base class for all the controllers in the viewcontrollers'
 * package of this app.  The ViewController classes are controllers which take care of control initialization,
 * form behavior and processing for the Scene to which it has been assigned.
 *
 * Each ViewController sub class must have a property which contains an instance
 * of the object which it must communicate requests to in the model layer.  This property
 * is thus initialized by a class that must implement a ViewOperation interface sub class.
 */

public abstract class ViewController {

    protected List<TextInputControl> requiredFields;
    protected ViewOperation operation;

    //setters and getters


    /**
     * setOperation - Abstract method to be overridden by all child subclasses to set their
     * ViewOperation interface type property.
     *
     * @param operation - ViewOperation - an instance of a Operation class which implements
     *                  a ViewOperation subclass.
     */
    public abstract void setOperation(ViewOperation operation);

    /**
     * getRequiredFields creates a Singleton list for each ViewController sub class to be able to
     * add controls which are used to obtain required information - hence are required fields.
     * Generally, these are controls that are TextInputControl types (ex. TextField, TextArea).
     * The list can then be used along with the utility class GUIUtility to perform various field
     * validation.
     *
     * @return List<TextInputControl> - either the already initialized list of required fields
     * or a newly created singleton instance of the list.
     */
    public List<TextInputControl> getRequiredFields() {
        return (requiredFields == null ?
                requiredFields = new ArrayList<TextInputControl>()
                : requiredFields);
    }
}
