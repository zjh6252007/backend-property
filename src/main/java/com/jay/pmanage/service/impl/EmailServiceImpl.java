package com.jay.pmanage.service.impl;

import com.jay.pmanage.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendVerificationEmail(String to, String subject, String verificationUrl) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
        String htmlMsg = "<!DOCTYPE html>"
                + "<html><head>"
                + "<title>Verify Email</title>"
                + "</head><body>"
                + "<h3>Please click the following link to verify your email address:</h3>"
                + "<a href='" + verificationUrl + "' target='_blank'>Verify Email</a>"
                + "</body></html>";

        try{
            helper.setText(htmlMsg,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("jayemailservice@gmail.com"); // Change to your email address
            javaMailSender.send(mimeMessage);
        }catch(MessagingException e){
            System.out.println("Error sending HTML email: " + e.getMessage());
        }
    }
}
