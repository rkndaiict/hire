package com.common.base.converters;

import java.lang.reflect.Method;

import org.dozer.DozerConverter;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("rawtypes")
public class StringEnumCustomConverter extends DozerConverter<String, Enum> {

	private static final Logger logger = LoggerFactory.getLogger(StringEnumCustomConverter.class);

	public StringEnumCustomConverter() {
		super(String.class, Enum.class);
	}

	@Override
	public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
		if (source == null) {
			return null;
		}
		if (destinationClass != null) {
			if (destinationClass.getSimpleName().equalsIgnoreCase("String")) {
				return getString(source);
			} else if (destinationClass.isEnum()) {
				return getEnum(destinationClass, source);
			} else {
				throw new MappingException(new StringBuilder("Converter ").append(this.getClass().getSimpleName()).append(" was used incorrectly. Arguments were: ")
						.append(destinationClass.getClass().getName()).append(" and ").append(source).toString());
			}
		}
		return null;
	}

	private Object getString(Object object) {
		return object.toString();
	}

	private Object getEnum(Class<?> destinationClass, Object source) {
		Object enumeration = null;

		Method[] ms = destinationClass.getMethods();
		for (Method m : ms) {
			if (m.getName().equalsIgnoreCase("customValueOf")) {
				try {
					enumeration = m.invoke(destinationClass.getClass(), (String) source);
				} catch (Exception e) {
					logger.warn("Error while converting from String " + source + " to Enum " + destinationClass.getClass().getName(), e.getMessage());
				}
			}else if (m.getName().equalsIgnoreCase("valueOf")) {
				try {
					enumeration = m.invoke(destinationClass.getClass(), (String) source);
				} catch (Exception e) {
					logger.warn("Error while converting from String " + source + " to Enum "
							+ destinationClass.getClass().getName(), e.getMessage());
				}
			}
			if(enumeration != null)
				return enumeration;
		}
		return null;
	}

	@Override
	public Enum convertTo(String source, Enum destination) {
		return null;
	}

	@Override
	public String convertFrom(Enum source, String destination) {
		return null;
	}
}
