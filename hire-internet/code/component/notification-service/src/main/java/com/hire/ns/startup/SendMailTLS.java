package com.hire.ns.startup;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hire.ns.domain.Email;

public class SendMailTLS {
	
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage mailMessage;
 
	public static void main(String args[]) throws AddressException, MessagingException {
		Email email = new Email();
		email.setToEmail("harshul.varshney@gmail.com");
		email.setFromEmail("harshul.varshney@gmail.com");
		email.setCcEmail("test@gmail.com");
		email.setSubject("TEST EMAIL");
		sendEmail(email, "Hi All");
	}
 
	public static void sendEmail(Email email, String Body) throws AddressException, MessagingException {
 
		final String username = "harshul.varshney@gmail.com";
		final String password = "wazzjemrjrsfwlcg";
		
		//setup Mail Server Properties..
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
 
		//get Mail Session..
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		mailMessage = new MimeMessage(getMailSession);
		mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getToEmail()));
		if(email.getCcEmail() != null) {
			mailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(email.getCcEmail()));
		}
		mailMessage.setSubject(email.getSubject());
		mailMessage.setContent(email.getBody().toString(), "text/html");
 
		//Get Session and Send mail
		Transport transport = getMailSession.getTransport("smtp");
 
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", username, password);
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		transport.close();
	}
}
