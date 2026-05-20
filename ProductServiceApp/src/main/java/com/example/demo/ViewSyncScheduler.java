package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ViewSyncScheduler {

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void syncViews() {
        System.out.println("Scheduler running: syncing views...");
    }
    
    
}