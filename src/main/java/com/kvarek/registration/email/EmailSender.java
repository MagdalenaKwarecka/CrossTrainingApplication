package com.kvarek.registration.email;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
