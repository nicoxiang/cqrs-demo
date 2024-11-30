package com.techbank.cqrs.core.handlers;

import com.techbank.cqrs.core.domain.AggregateRoot;

/**
 * The event sourcing handler provides an interface abstraction through which the command handler can obtain
 * the latest state of the aggregate.
 * and through which it can persist the uncommitted changes of the aggregate as events to the event store.
 * It basically sits between the command handler and the Events Store business logic.
 */
public interface EventSouringHandler<T> {
    void save(AggregateRoot aggregateRoot);
    T getById(String id);
    void republishEvents();
}
