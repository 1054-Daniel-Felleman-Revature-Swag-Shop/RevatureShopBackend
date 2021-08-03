package com.revature.shop;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.revature.shop.models.Account;

@Service
@PropertySource("classpath:email.properties")
public class MailService {
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private final String imageURL = "https://rss-images.s3.us-east-2.amazonaws.com/images/";
    private String pointsTemplate, saleTemplate, newItemTemplate;
    private Logger logger = LogManager.getLogger(MailService.class);

    @Autowired
    public MailService(@Value("${email.host}") String host,
                       @Value("${email.port}") int port,
                       @Value("${email.username}") String username,
                       @Value("${email.password}") String password) throws IOException {
        this.mailSender.setHost(host);
        this.mailSender.setPort(port);

        this.mailSender.setUsername(username);
        this.mailSender.setPassword(password);
                
        Properties props = this.mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("points_email.html");
        this.pointsTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
        
        stream = this.getClass().getClassLoader().getResourceAsStream("sale_email.html");
        this.saleTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
        
        stream = this.getClass().getClassLoader().getResourceAsStream("new_item_email.html");
        this.newItemTemplate = StreamUtils.copyToString(stream, Charset.defaultCharset());
    }

    public void sendEmail(String to, String subject, String body) {
    	try {
	        this.mailSender.send(mail -> {
	            mail.setFrom("RevatureShop <" + this.mailSender.getUsername() + ">");
	            mail.setRecipients(Message.RecipientType.TO, to);
	
	            mail.setSubject(subject);
	            mail.setContent(body, "text/html");
	        });
    	} catch (Exception e) {
    		this.logger.error(e.getStackTrace());
    	}
    }
    
    public void sendPointsEmail(String receiver, String name, String points, String reason) {
    	String email = this.pointsTemplate.replaceAll("\\{\\{NAME}}", name)
    			.replaceAll("\\{\\{POINTS}}", points)
    			.replaceAll("\\{\\{REASON}}", reason);
    	this.sendEmail(receiver, "RevatureShop Points", email);
    }
    
    public void sendSaleEmails(List<Account> receivers, String itemName, String itemDiscount) {
    	receivers.forEach(account -> this.sendSaleEmail(account.getEmail(), account.getName(), itemName, itemDiscount));
    }
    
    public void sendSaleEmail(String receiver, String name, String itemName, String itemDiscount) {
    	String email = this.saleTemplate.replaceAll("\\{\\{NAME}}", name)
    			.replaceAll("\\{\\{ITEM_NAME}}", itemName)
    			.replaceAll("\\{\\{ITEM_DISCOUNT}}", itemDiscount)
    			.replaceAll("\\{\\{ITEM_IMAGE}}", this.imageURL + itemName + ".png");   		
    	this.sendEmail(receiver, "RevatureShop Sale", email);
    }
    
    public void sendNewItemEmails(List<Account> receivers, String itemName, String points) {
    	receivers.forEach(account -> this.sendNewItemEmail(account.getEmail(), account.getName(), itemName, points));
    }
    
    public void sendNewItemEmail(String receiver, String name, String itemName, String points) {
    	String email = this.newItemTemplate.replaceAll("\\{\\{NAME}}", name)
    			.replaceAll("\\{\\{ITEM_NAME}}", itemName)
    			.replaceAll("\\{\\{POINTS}}", points)
    			.replaceAll("\\{\\{ITEM_IMAGE}}", this.imageURL + itemName + ".png");
    	this.sendEmail(receiver, "RevatureShop New Item", email);
    }
}
