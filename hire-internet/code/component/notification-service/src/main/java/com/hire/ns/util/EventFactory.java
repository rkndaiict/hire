package com.hire.ns.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.service.api.EventStrategy;
import com.hire.ns.service.impl.EmployeeEnrollmentReminderEmailEvent;
import com.hire.ns.service.impl.NewHireEnrollmentReminderEmailEvent;
import com.hire.ns.service.impl.ResendEmployeeRegistrationLinkEvent;
import com.hire.ns.service.impl.ResendRegistrationMailLinkEvent;

/**
 * The Class EmailUtil.
 */
@Component
public final class EventFactory {
	
	@Autowired
	private EmployeeEnrollmentReminderEmailEvent employeeEnrollmentReminderEmailEvent;
	
	@Autowired
	private NewHireEnrollmentReminderEmailEvent newHireEnrollmentReminderEmailEvent;
	
	@Autowired
	private ResendRegistrationMailLinkEvent resendRegistrationMailLinkEvent;
	
	@Autowired
	private ResendEmployeeRegistrationLinkEvent resendEmployeeRegistrationLinkEvent;

	public static enum NOTIFICTAION_EVENT_TYPE {

		CLIENT_ADD("Client Added"), 
		AGENCY_ADDED_BROKER("AGENCY_ADDED_BROKER"), 
		EXCHANGE_ADDED_BROKER("Exchange Added Broker"),
		EXCHANGE_ADDED_AGENCY("Exchange Added Agency"), 
		ADD_BROKER_BY_AGENCY_NOTIFICATION("Add Broker By Agency"), 
		ADD_SUB_BROKER_BY_AGENCY_NOTIFICATION("Add Sub Broker By Agency"),
		EP("Employer Publish"),
		ADD_CARRIER_IN_EXCHANGE("Add Carrier In Exchange"), 
		OEP_UPDATE("OEP Update"), 
		OEP_UPDATE_PRE_OEP_START("OEP Update Pre OEP Start"),
		EMPLOYEE_EMAIL_GROUP_ID_REQ("EMPLOYEE_EMAIL_GROUP_ID_REQ"),
		EMPLOYEE_EMAIL_GROUP_ID_NOREQ("EMPLOYEE_EMAIL_GROUP_ID_NOREQ"), 
		EMPLOYEE_ENROLLMENT_REMINDER("EMPLOYEE_ENROLLMENT_REMINDER"),
		NEW_HIRE_ENROLLMENT_REMINDER("NEW_HIRE_ENROLLMENT_REMINDER"),
		ONBOARDED_ER_REG_LINK("ONBOARDED_ER_REG_LINK"), 
		ONBOARDED_IND_REG_LINK("ONBOARDED_INDV_EMAIL_LINK"), 
		ADD_EMPLOYER_USER_REG("ADD_EMPLOYER_USER_REG"),
		AGENCY_ADDED_AGENCY_USER("AGENCY_ADDED_AGENCY_USER"),
		RESEND_REGISTRATION_LINK("RESEND_REGISTRATION_LINK"),
		EMPLOYEE_RESEND_REGISTRATION_LINK("ResendRegistrationLink"),
		OPPORTUNITY_FOR_NEW_HIRE_SEP("OPPORTUNITY_FOR_NEW_HIRE_SEP");

		/** The type. */
		private String value;

		NOTIFICTAION_EVENT_TYPE(String value) {

			this.value = value;
		}

		public String getValue() {

			return value;
		};
	}

	public EventStrategy getEventType(NotificationDTO notificationDTO) {
		if (notificationDTO.getEvent().equalsIgnoreCase(NOTIFICTAION_EVENT_TYPE.CLIENT_ADD.getValue())) {
			return null;
		}
		else if (notificationDTO.getEvent().equalsIgnoreCase(NOTIFICTAION_EVENT_TYPE.EMPLOYEE_ENROLLMENT_REMINDER.getValue())) {
			return employeeEnrollmentReminderEmailEvent;
		}
		else if (notificationDTO.getEvent().equalsIgnoreCase(NOTIFICTAION_EVENT_TYPE.NEW_HIRE_ENROLLMENT_REMINDER.getValue())) {
		    return newHireEnrollmentReminderEmailEvent;
		}
		else if(notificationDTO.getEvent().equalsIgnoreCase(NOTIFICTAION_EVENT_TYPE.RESEND_REGISTRATION_LINK.getValue())) {
			return resendRegistrationMailLinkEvent;
		}
		else if (notificationDTO.getEvent().equalsIgnoreCase(NOTIFICTAION_EVENT_TYPE.EMPLOYEE_RESEND_REGISTRATION_LINK.getValue())){
			return resendEmployeeRegistrationLinkEvent;
		}
		
		return null;
	}
}
