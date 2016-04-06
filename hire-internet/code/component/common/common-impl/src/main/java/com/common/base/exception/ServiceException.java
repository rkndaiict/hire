package com.common.base.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(ServiceException.class);

	private List<Error> errors = new ArrayList<Error>(0);

	public ServiceException(Error error, Exception e) {
		super(e);
		this.errors.add(error);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(Error error) {
		printLogs(error);
		this.errors.add(error);
	}

	public ServiceException(List<Error> errors) {
		printLogs(errors);
		this.errors = errors;
	}

	public ServiceException() {
		// TODO Auto-generated constructor stub
	}

	public List<Error> getHireErrorDetails() {
		return errors;
	}

	public void addErrors(List<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		Iterator<Error> itr = errors.iterator();
		StringBuilder sb = new StringBuilder();
		while (itr.hasNext()) {
			Error error = itr.next();
			sb.append("error code = ");
			sb.append(error.getErrorCode());
			sb.append(" , detail message = ");
			sb.append(error.getDetailMessage());
			sb.append(" , entity = ");
			sb.append(error.getEntity() == null ? "null" : error.getEntity()
					.toString());
			sb.append("\n").append(super.toString()).append("\n");
		}
		return sb.toString();
	}

	private void printLogs(Error error) {
		if (error != null) {
			String errorMessage = error.getDetailMessage();
			if (errorMessage != null) {
				logger.info(errorMessage);
			}
		}
	}

	private void printLogs(List<Error> errorList) {
		if (errorList != null) {
			for (Error error : errorList) {
				printLogs(error);
			}
		}
	}

}
