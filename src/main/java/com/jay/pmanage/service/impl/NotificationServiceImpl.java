package com.jay.pmanage.service.impl;

import com.jay.pmanage.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    NotificationServiceImpl (SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @Override
    public void notifyOwners(String message) {
        simpMessagingTemplate.convertAndSend("/topic/repairs",message);
    }
}
