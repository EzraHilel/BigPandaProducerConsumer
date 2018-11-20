package com.javacode.assignment.service;

import com.javacode.assignment.model.ReproducedEvent;

public interface IRestEventService {
    public void eventUpdate(ReproducedEvent event) throws InterruptedException;
    public EventStatistics getStat();
}