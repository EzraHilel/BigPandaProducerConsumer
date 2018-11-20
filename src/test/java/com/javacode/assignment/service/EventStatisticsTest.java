package com.javacode.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.javacode.assignment.model.ReproducedEvent;

public class EventStatisticsTest {

    @Test
    public void f() throws InterruptedException {
        EventStatistics tmp = new EventStatistics();
        
        int numOfThread = 4;
        int numOfIter = 10000000;
        List<Thread> threadList = new ArrayList<>();
        
        for (int i = 0; i < numOfThread; i++) {
            Thread a = new Thread(new EventPut(numOfIter, tmp));
            threadList.add(a);
        }
        
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).start();
        }
        
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).join();
        }
        
        Assert.assertEquals(numOfThread * numOfIter, tmp.getDataName("bla"));
        Assert.assertEquals(numOfThread * numOfIter, tmp.getEventType("foo"));
    }
    
    class EventPut implements Runnable {

        
        private EventStatistics eventStat;
        private ReproducedEvent event;
        private int numOfIter;

        public EventPut(int numOfIter, EventStatistics eventStat) {
            this.numOfIter = numOfIter;
            this.eventStat = eventStat;
            this.event = new ReproducedEvent();
            this.event.setEvent_type("foo");
            this.event.setData("bla");
        }
        
        @Override
        public void run() {
            for (int i = 0; i < numOfIter; i++) {
                eventStat.updateStatistics(event);
            }
            
        }
        
    }
}
