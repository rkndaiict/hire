package com.hire.common.module.event;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

public abstract class AbstractEvent extends ApplicationEvent {
	public static final Logger logger = LoggerFactory.getLogger(AbstractEvent.class);
	private static final long serialVersionUID = 1L;

	protected String eventId;

	protected Object eventContext;
	
	/**
	 * Flag to indicate whether this event has to be 
	 * processed asynchronously or not.
	 */
	protected boolean asyncEvent;

	/**
	 * Instantiates a new abstract event.
	 *
	 * @param eventId the event id
	 * @param eventContext the event context
	 */
	public AbstractEvent(String eventId, Object eventContext) {
		super(eventId);
		logger.info("Event fired against -> EventId:: "+ eventId + "; EventContext:: "+ eventContext);
		this.eventId = eventId;
		this.eventContext = eventContext;
		this.asyncEvent = false;
	}
	
	public AbstractEvent(String eventId, boolean asyncEvent, Object eventContext) {
        super(eventId);
        logger.info("Event created for -> EventId:: {}; EventContext:: {}", eventId, eventContext);
        this.eventId = eventId;
        this.eventContext = eventContext;
        this.asyncEvent = asyncEvent;
    }

	/**
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Gets the event context.
	 *
	 * @return the event context
	 */
	public Object getEventContext() {
		return eventContext;
	}
	
	
	/**
     * @return Returns the asyncEvent.
     */
    public boolean isAsyncEvent() {
    
        return asyncEvent;
    }

    /**
     * @param asyncEvent The asyncEvent to set.
     */
    public void setAsyncEvent(boolean asyncEvent) {
    
        this.asyncEvent = asyncEvent;
    }

    /**
	 * Gets the context params.
	 *
	 * @param paramName the param name
	 * @return the context params
	 */
	@SuppressWarnings("unchecked")
	public Object getContextParams(String paramName) {
		if(eventContext == null || !(eventContext instanceof Map)) {
			return null;
		}
		return ((Map<String, Object>)eventContext).get(paramName);
	}
}
