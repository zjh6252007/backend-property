package com.jay.pmanage.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void notifyOwners(String message);
}
