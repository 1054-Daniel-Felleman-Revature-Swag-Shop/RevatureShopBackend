package com.revature.shop;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.mail.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
@PropertySource("classpath:email.properties")
public class MailService {
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private final String imageURL = "https://rss-images.s3.us-east-2.amazonaws.com/images/";
    private String pointsTemplate, saleTemplate, newItemTemplate;

    @Autowired
    public MailService(@Value("${email.host}") String host,
                       @Value("${email.port}") int port,
                       @Value("${email.username}") String username,
                       @Value("${email.password}") String password) throws IOException {
        this.mailSender.setHost(host);
        this.mailSender.setPort(port);

        this.mailSender.setUsername(username);
        this.mailSender.setPassword(password);
        
//        System.out.printf("Initializing MailService with information: host=%s, port=%d, username=%s, password=%s.%n", host, port, username, password);
        
        Properties props = this.mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("points_email.html");
        this.pointsTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
        
        stream = this.getClass().getClassLoader().getResourceAsStream("sale_email.html");
        this.saleTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
        
        stream = this.getClass().getClassLoader().getResourceAsStream("new_item_email.html");
        this.newItemTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
    }

    public void sendRegistration(String to, String subject, String body) {
    	System.out.printf("Sending email to %s with subject %s.%n", to, subject);
    	
        this.mailSender.send(mail -> {
            mail.setFrom("RevatureShop <" + this.mailSender.getUsername() + ">");
            mail.setRecipients(Message.RecipientType.TO, to);

            mail.setSubject(subject);
            mail.setContent(body, "text/html");
        });
    }
    
    public void sendPointsEmail(String receiver, String name, String points, String reason) {
    	String email = this.pointsTemplate.replaceAll("\\{\\{NAME}}", name)
    			.replaceAll("\\{\\{POINTS}}", points)
    			.replaceAll("\\{\\{REASON}}", reason);
    	this.sendRegistration(receiver, "RevatureShop Points", email);
    }
    
    public void sendSaleEmail(String receiver, String name, String itemName, String itemDiscount) {
    	String email = this.saleTemplate.replaceAll("\\{\\{NAME}}", name)
    			.replaceAll("\\{\\{ITEM_NAME}}", itemName)
    			.replaceAll("\\{\\{ITEM_DISCOUNT}}", itemDiscount)
    			.replaceAll("\\{\\{ITEM_IMAGE}}", this.imageURL + itemName + ".png");
    	this.sendRegistration(receiver, "RevatureShop Sale", email);
    }
    
    public void sendNewItemEmail(String receiver, String name, String itemName, String points) {
    	String email = this.newItemTemplate.replaceAll("\\{\\{NAME}}", name)
    			.replaceAll("\\{\\{ITEM_NAME}}", itemName)
    			.replaceAll("\\{\\{POINTS}}", points)
    			.replaceAll("\\{\\{ITEM_IMAGE}}", this.imageURL + itemName + ".png");
    	this.sendRegistration(receiver, "RevatureShop New Item", email);
    }
}
