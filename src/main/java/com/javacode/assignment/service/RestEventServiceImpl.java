package com.javacode.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.javacode.assignment.model.ReproducedEvent;

@Service
public class RestEventServiceImpl implements IRestEventService {
    
    private final Logger LOG = LoggerFactory.getLogger(RestEventServiceImpl.class);
    
    private EventStatistics stats = new EventStatistics();
    
    @Override
    public void eventUpdate(ReproducedEvent event) throws InterruptedException {
        
//        Thread.sleep(3000);

        LOG.info("Event: {} reached event service", event);
        
        stats.updateStatistics(event);
        
    }

    @Override
    public EventStatistics getStat() {
        return stats;
    }
}