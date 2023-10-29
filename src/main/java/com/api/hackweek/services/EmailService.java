package com.api.hackweek.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendEmail(String emailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    private String generateResetPasswordText(String token) {
        return "Para redefinir sua senha, copie o código abaixo e cole no aplicativo:\n\n" + token;
    }

    public void sendResetPasswordEmail(String emailTo, String token) {
        sendEmail(emailTo, "Redefinição de senha", generateResetPasswordText(token));
    }
}
