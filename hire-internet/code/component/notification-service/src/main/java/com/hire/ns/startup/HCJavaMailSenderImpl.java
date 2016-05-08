package com.hire.ns.startup;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * The Class to load mail properties from DB
 * 
 */

public class HCJavaMailSenderImpl  extends  JavaMailSenderImpl{
	
	private final String MAIL_SMPT_HOST = "mail.smtp.host";
	private final String MAIL_SMPT_PORT = "mail.smtp.port";
	private final String MAIL_SMPT_USER = "mail.smtp.user";
	private final String MAIL_SMPT_PASS = "mail.smtp.pass";
	private final String MAIL_SMPT_AUTH = "mail.smtp.auth";
	private final String MAIL_SMPT_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	
	Properties mailProperties = new Properties();
	
	public HCJavaMailSenderImpl() {
		
	}
	
	@PostConstruct
	public void populateProperties() {
//		mailProperties.put(MAIL_SMPT_AUTH, getMailSmtpAuth());
//		mailProperties.put(MAIL_SMPT_STARTTLS_ENABLE, getMailSmtpStarttlsEnable());
		this.setJavaMailProperties(mailProperties);
	}
}
