package com.techbank.cqrs.core.events;

import com.techbank.cqrs.core.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEvent extends Message {
    /**
     * Important when we replay the event source to recreate the state of the aggregate.
     * It will also enable us to properly implement optimistic concurrency control
     */
    private int version;
}
