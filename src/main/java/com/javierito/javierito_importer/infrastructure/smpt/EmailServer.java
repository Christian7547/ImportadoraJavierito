package com.javierito.javierito_importer.infrastructure.smpt;

import com.javierito.javierito_importer.domain.ports.output.IEmailServer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServer implements IEmailServer {
    private final JavaMailSender mailSender;
    private final EmailBody emailBody;

    @Value("${spring.mail.username}")
    private String subject;

    public EmailServer(JavaMailSender mailSender, EmailBody emailBody) {
        this.mailSender = mailSender;
        this.emailBody = emailBody;
    }

    public void sendEmail(String to, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }

    @Override
    public void sendCredentials(String to, String name, String lastName, String userName, String password) {
        String message = emailBody.BuildCredentialBody(name, lastName, userName, password);
        sendEmail(to, message);
    }
}
