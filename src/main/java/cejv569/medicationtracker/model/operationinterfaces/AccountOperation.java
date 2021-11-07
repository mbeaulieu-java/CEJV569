package cejv569.medicationtracker.model.operationinterfaces;

import cejv569.medicationtracker.exceptions.OperationFailureException;
import cejv569.medicationtracker.model.datainterfaces.User;

public interface AccountOperation extends ViewOperation{
    boolean putData(User data) throws OperationFailureException;

}
