package com.hire.ns.repository.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hire.ns.domain.Email;
import com.hire.ns.repository.EmailRepositoryCustom;

public class EmailRepositoryCustomImpl implements EmailRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Email> findAllByMessageTypeAndStatus(String messageType, int pageSize) {
		Query query = ((Session) entityManager.getDelegate())
            .createQuery(
                    "select e from Email e, NsNotificationstatus ns where e.emailid = ns.nsMessageid "
                            + "and ns.nsMessagetype =:messageType "
                            + "and ns.nsStatusreference=:status "
                            + "and (ns.nsNumberofattempts = 0 "
                            		+ "or (ns.nsDatelastattempt is not null "
                            				+ "and ns.nsDatelastattempt <= :nextAttemptTime)"
                            		+ ") "
                            + "order by e.emailid")
            .setParameter("messageType", messageType)
            .setParameter("nextAttemptTime", getAttemptTime())
            .setMaxResults(pageSize);
    return query.list();
	}
	
	private Calendar getAttemptTime(){
		Calendar c = Calendar.getInstance();
//		c.add(Calendar.SECOND, -getEmailRetryInterval());
		return c;
	}
	
}
