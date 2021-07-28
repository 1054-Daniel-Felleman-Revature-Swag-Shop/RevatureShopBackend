package com.revature.shop;

import java.util.Properties;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:email.properties")
public class MailService {
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Autowired
    public MailService(@Value("${email.host: localhost}") String host,
                       @Value("${email.port: 25}") int port,
                       @Value("${email.username: username}") String username,
                       @Value("${email.password: password}") String password) {
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    public void sendRegistration(String to, String subject, String body) {
        mailSender.send(mail -> {
            mail.setFrom("RevatureShop <" + mailSender.getUsername() + ">");
            mail.setRecipients(Message.RecipientType.TO, to);

            mail.setSubject(subject);
            mail.setContent(body, "text/html");
        });
    }
}