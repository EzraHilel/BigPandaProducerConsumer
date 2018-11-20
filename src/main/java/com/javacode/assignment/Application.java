package com.javacode.assignment;

import static reactor.bus.selector.Selectors.$;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.bus.Event;
import reactor.bus.EventBus;

import com.google.gson.Gson;
import com.javacode.assignment.handler.EventHandler;
import com.javacode.assignment.model.ReproducedEvent;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    private final EventBus eventBus;
    private final EventHandler eventHandler;
  
    private Gson gson = new Gson();
   
    @Autowired
    public Application(EventBus eventBus, EventHandler eventHandler) {
        this.eventBus = eventBus;
        this.eventHandler = eventHandler;
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            LOG.error("Generator full path is missing");
            return;
        }
        
        if(!(new File(args[0])).exists()) {
            LOG.error("Generator isn't exists, {}", args[0]);
            return;
        }
        
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        eventBus.on($("eventHandler"), eventHandler);

        Process p = Runtime.getRuntime().exec(strings[0]);
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        String line;
        while ((line = bri.readLine()) != null) {
            try {
                ReproducedEvent event = gson.fromJson(line, ReproducedEvent.class);
                eventBus.notify("eventHandler", Event.wrap(event));
            } catch (Exception e ) {
                LOG.error("mal format {}", line);
                continue;
            }
        }
        bri.close();
    }
}