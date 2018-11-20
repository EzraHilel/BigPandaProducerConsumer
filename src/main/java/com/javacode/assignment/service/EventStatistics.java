package com.javacode.assignment.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.javacode.assignment.model.ReproducedEvent;

public class EventStatistics {

    public Map<String, AtomicInteger> eventType = new ConcurrentHashMap<>();
    public Map<String, AtomicInteger> dataName = new ConcurrentHashMap<>();
    
    public void updateStatistics(ReproducedEvent event) {
        AtomicInteger value = eventType.get(event.getEvent_type());
        if(value == null) {
            eventType.put(event.getEvent_type(), new AtomicInteger(1));
        } else {
            value.incrementAndGet();
            eventType.put(event.getEvent_type(), value);
        }
        
        
        value = dataName.get(event.getData());
        if(value == null) {
            dataName.put(event.getData(), new AtomicInteger(1));
        } else {
            value.incrementAndGet();
            dataName.put(event.getData(), value);
        }
            
    }
    
    public int getEventType(String evetName) {
        return eventType.get(evetName).get();
    }

    public int getDataName(String dataValue) {
        return dataName.get(dataValue).get();
    }
}
