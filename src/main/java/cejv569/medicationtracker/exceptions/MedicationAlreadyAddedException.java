package cejv569.medicationtracker.exceptions;

public class MedicationAlreadyAddedException extends Exception{

    private String message;

    public MedicationAlreadyAddedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

