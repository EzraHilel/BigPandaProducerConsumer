package com.javacode.assignment.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.fn.Consumer;

import com.javacode.assignment.model.ReproducedEvent;
import com.javacode.assignment.service.IRestEventService;

@Service
public class EventHandler implements Consumer<Event<ReproducedEvent>> {
    
    static final Logger LOG = LoggerFactory.getLogger(EventHandler.class);
    
    private final IRestEventService restEventService;

    @Autowired
    public EventHandler(IRestEventService restEventService) {
        this.restEventService = restEventService;
    }

    @Override
    public void accept(Event<ReproducedEvent> restEvent) {
        ReproducedEvent reproducedEvent = restEvent.getData();
        try {
            restEventService.eventUpdate(reproducedEvent);
        } catch (InterruptedException e) {
            LOG.error("Something went realy wrong");
        }
    }
}