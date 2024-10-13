package com.techbank.cqrs.core.domain;

import com.techbank.cqrs.core.events.BaseEvent;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Aggregate Root is the entity within the aggregate that is responsible for maintaining this consistent state.
 */
public abstract class AggregateRoot {
    private String id;
    private int version = -1;
    /**
     * contain all the changes that are made to the aggregate in the form of events
     */
    private final List<BaseEvent> changes = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<BaseEvent> getUncommittedChanges() {
        return changes;
    }

    /**
     * Clear the changes list to make sure that the next time that
     * we add events to the list of changes, that they are legitimate new changes
     */
    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}", event.getClass().getName()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error applying event to aggregate");
        } finally {
            if(isNewEvent) {
                changes.add(event);
            }
        }
    }

    /**
     * New event being raised
     */
    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    /**
     * Recreate the state of the aggregate
     */
    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> {applyChange(event, false);});
    }
}
