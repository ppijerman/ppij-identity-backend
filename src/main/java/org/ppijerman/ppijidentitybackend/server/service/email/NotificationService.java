package org.ppijerman.ppijidentitybackend.server.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class NotificationService {

    // Simple class which does the mail handling
    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void notifyUser(String email, String content) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("admin@spring.io");        // static address
        mail.setSubject("Email Confirmation");  // static subject
        mail.setText(content);
        mail.setTo(email);

        this.javaMailSender.send(mail);
    }
}
