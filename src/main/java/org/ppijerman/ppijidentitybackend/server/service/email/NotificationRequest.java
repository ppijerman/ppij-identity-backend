package org.ppijerman.ppijidentitybackend.server.service.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NotificationRequest {

    /*
    private String from;
    private String to;
    private String subject;
    private String content;

    public NotificationRequest(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from = " + from + '\'' +
                ", to = " + to + '\'' +
                ", subject = " + subject + '\'' +
                ", content = " + content + '\'' +
                '}';
    }
    */

    @Email
    private String email;

    @NotBlank
    private String content;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
