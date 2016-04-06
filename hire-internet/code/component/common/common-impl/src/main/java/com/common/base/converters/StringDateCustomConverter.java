package com.common.base.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringDateCustomConverter extends DozerConverter<String, Date> {

	private static final Logger logger = LoggerFactory.getLogger(StringDateCustomConverter.class);

	private final String dateFormat = "MM/dd/yyyy";

	public StringDateCustomConverter() {
		super(String.class, Date.class);
	}

	@Override
	public Date convertTo(String str, Date date) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			return new SimpleDateFormat(dateFormat).parse(str);
		} catch (Exception e) {
			logger.error("Error converting String " + str + " to Date", e);
			return null;
		}
	}

	@Override
	public String convertFrom(Date date, String str) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(dateFormat).format(date);
	}
}
