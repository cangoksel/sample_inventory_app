package com.github.cangoksel.common.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by herdemir on 10.04.2015.
 */
@Component
public class DomainEvents {
    private static ApplicationEventPublisher eventPublisher;

    @Autowired
    public DomainEvents(ApplicationEventPublisher eventPublisher) {
        DomainEvents.eventPublisher = eventPublisher; //NOSONAR
    }

    public static void post(Object event) {
        Assert.notNull(event,"Event boş olmamalı.");
        eventPublisher.publishEvent(event);
    }
}
