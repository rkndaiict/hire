package com.hire.ns.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.hire.ns.domain.EmailRecipientType;
import com.hire.ns.domain.MessageTemplate;
import com.hire.ns.repository.MessageTemplateRepositoryCustom;

public class MessageTemplateRepositoryCustomImpl implements MessageTemplateRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public MessageTemplate findByEventAndRecipientType(String event, EmailRecipientType recipientType) {
    	
        Session session = (Session) entityManager.getDelegate();
        
        String queryString = "select u from MessageTemplate u "
                + "where u..event = :event "
                + "and u.recipientType = :event";
        
        Query query = session .createQuery(queryString);

        query.setString("event", event);
        query.setParameter("recipientType", recipientType);

        query.setMaxResults(1);
        MessageTemplate messagetemplate = (MessageTemplate) query.uniqueResult();
        return messagetemplate;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MessageTemplate> findAllMessageTemplate() {
        Session session = (Session) entityManager.getDelegate();

        Query query = session
                .createQuery("select u from NsMessagetemplate u order by u.id desc");

        @SuppressWarnings("unchecked")
		List<MessageTemplate> messagetemplateList = (List<MessageTemplate>) query.list();
        
        return messagetemplateList;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MessageTemplate> getEmailTemplateByRecipientType(EmailRecipientType emailRecipientType){
    	
    	Session session = (Session) entityManager.getDelegate();

    	String queryString = "select u from MessageTemplate u ";
    	if(emailRecipientType != null){
    		queryString += " where u.emailRecipientType= :emailRecipientType";
    	}
    	queryString +=" order by u.id desc";
    	
        Query query = session.createQuery(queryString);
        query.setParameter("emailRecipientType", emailRecipientType);

        List<MessageTemplate> messagetemplateList = (List<MessageTemplate>) query.list();
        
        return messagetemplateList;
    }

}
