package com.javacode.assignment.model;

public class ReproducedEvent {
    
    private String event_type;
    private String data;
    private long timestamp;
    
    public String getEvent_type() {
        return event_type;
    }
    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(event_type).append(" ").append(data).append(" ").append(timestamp);
        return sb.toString();
    }
    

}
