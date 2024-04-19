package com.jay.pmanage.service;

public interface EmailService {
    void sendVerificationEmail(String to, String subject, String verificationUrl);
}
