package com.kvarek.registration.email;

import javax.mail.MessagingException;

public interface EmailSender {
    void sendEmail(String to, String subject, String content) throws MessagingException;
}
