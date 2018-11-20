package com.javacode.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacode.assignment.service.EventStatistics;
import com.javacode.assignment.service.IRestEventService;

@RestController
public class EventStatisticsController {

    private final IRestEventService restEventService;
    
    @Autowired
    public EventStatisticsController(IRestEventService restEventService) {
        this.restEventService = restEventService;
    }
    
    @RequestMapping("/stats")
    public EventStatistics statistics() {
        return restEventService.getStat();
    }
}
