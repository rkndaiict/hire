package com.common.base.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
    private String detailMessage;
    
	@XmlElement
	private String errorCode;
   
	@XmlElement
	private Object entity;

	private Object[] arguments;

    public Error(String errorCode, String detailMessage, Object[] arguments) {
		super();
		this.detailMessage = detailMessage;
		this.errorCode = errorCode;
		this.arguments = arguments;
	}

	public Error(String []errocodewithDetailMessage , Object entity) {
        this(errocodewithDetailMessage[0] , errocodewithDetailMessage[1] , entity);
    }

    public Error(String errorCode, String detailMessage) {
        this(errorCode , detailMessage , null);
    }

    public Error(String errorCode, StringBuilder detailMessage) {
        this(errorCode , detailMessage.toString() , null);
    }

    @Deprecated
    public Error() {
    }
    
    public Error(String errorCode, String detailMessage, Object entity) {
        this.entity=entity;
        this.detailMessage=detailMessage;
        this.errorCode=errorCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArguments() {
		return arguments;
	}

	public Object getEntity() {
        return entity;
    }

    public String getErrorDetailString(){
        StringBuffer errDetailString = new StringBuffer();
        errDetailString.append("[");
        errDetailString.append(errorCode);
        errDetailString.append(", ");
        if (entity != null) {
            errDetailString.append(entity);
            errDetailString.append(", ");
        }
        errDetailString.append(detailMessage);
        errDetailString.append("]");
        return errDetailString.toString();
    }
}
