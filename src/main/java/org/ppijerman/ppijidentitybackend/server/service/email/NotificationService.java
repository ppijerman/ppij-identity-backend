package org.ppijerman.ppijidentitybackend.server.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // Simple class which does the mail handling
    @Autowired
    private JavaMailSender javaMailSender;

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

    // TODO: Add methods to create confirmation token and mail builder to send email confirmation

    /*
    public void sendMessage(NotificationRequest mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        javaMailSender.send(message);
    }

     */

}
