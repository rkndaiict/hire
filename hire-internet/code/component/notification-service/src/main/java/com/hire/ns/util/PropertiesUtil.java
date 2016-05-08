package com.hire.ns.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>PropertiesUtil</code> class can be used to get the property value
 * from {env}.properties file
 */
public final class PropertiesUtil {

	

	/** The tegrisfire properties. */
	private final Properties nsProperties = new Properties();

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * Instantiates a new properties util.
	 */
	private PropertiesUtil() {
		try {
			InputStream is = PropertiesUtil.class.getResourceAsStream("/props/ns.properties");
			nsProperties.load(is);
			is.close();
		} catch (Exception e) {
			LOGGER.error("Could not load tegrisfire.properies file", e);
		}
	}

	/** The instance. */
	private static PropertiesUtil instance = new PropertiesUtil();
	/**
	 * Searches for the property with the specified key in this property list.
	 * The method returns <code>null</code> if the property is not found.
	 * 
	 * @param propertyName
	 *            the property key.
	 * @return the value in this property list with the specified key value.
	 */
	public static String getProperty(String propertyName) {
		return instance.nsProperties.getProperty(propertyName);
	}

	public static void main(String[] args) {
		String abc = PropertiesUtil.getProperty("db.url");
		System.out.println(abc);
	}
}
