package cejv569.medicationtracker.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * NOT YET USED, THE INTENDED PURPOSE IS TO GENERATE A CUSTOM EVENT THE APPLICATION WILL TRAP
 * WHEN A OperationFailureException OCCURS.
 */
public class UnexpectedErrorEvent extends Event {
    public UnexpectedErrorEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
