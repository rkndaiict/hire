package com.common.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javassist.Modifier;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.common.base.exception.ServiceException;
import com.common.base.exception.Error;

public final class CommonUtil {

	public static Double getValidNumericValue(Double value) {
		if (value == null) {
			value = 0.0;
		}
		return value;
	}

	public static Double getAverageDoubleValue(final Double value,
			final int noOfParticipants) {
		Double avg = getValidNumericValue(value);
		if (avg != 0) {
			avg = avg / noOfParticipants;
		}
		return avg;
	}

	public static <E> boolean checkIfMultipleElementsFound(final List<E> list) {
		boolean flag = false;
		if (null != list && !list.isEmpty() && list.size() > 1) {
			flag = true;
		}
		return flag;
	}

	public static <E> boolean checkIfNullOrEmpty(final Collection<E> list) {
		boolean flag = false;
		if (null == list || list.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	public static <E> Set<E> toSet(final List<E> l) {
		if (l != null) {
			return new HashSet<E>(l);
		} else {
			return null;
		}
	}

	public static <E> Set<E> toLinkedHashSet(final List<E> l) {
		if (l != null) {
			return new LinkedHashSet<E>(l);
		} else {
			return null;
		}
	}

	public static <E> List<E> toList(final Set<E> s) {
		return new ArrayList<E>(s);
	}

	public static void handleErrors(final List<Error> errors)
			throws ServiceException {
		if (errors != null && !errors.isEmpty()) {
			throw new ServiceException(errors);
		}
	}

	public static int getAgeFromDOB(final Date dateOfBirth) {
		return getAgeFromDOB(dateOfBirth, new Date());
	}

	public static int getAgeFromDOB(final Date dateOfBirth, final Date onDate) {
		final String dob = new SimpleDateFormat("MM-dd-yyyy")
				.format(dateOfBirth);
		if (dob == null || dob.length() < 10) {
			return -1;
		}
		final int yearDOB = Integer.parseInt(dob.substring(6, 10));
		final int dayDOB = Integer.parseInt(dob.substring(3, 5));
		final int monthDOB = Integer.parseInt(dob.substring(0, 2));

		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		final int onYear = Integer.parseInt(dateFormat.format(onDate));

		dateFormat = new SimpleDateFormat("MM");
		final int onMonth = Integer.parseInt(dateFormat.format(onDate));

		dateFormat = new SimpleDateFormat("dd");
		final int onDay = Integer.parseInt(dateFormat.format(onDate));

		int age = onYear - yearDOB;

		if (onMonth < monthDOB) {
			age = age - 1;
		}
		if (onMonth == monthDOB && onDay < dayDOB) {
			age = age - 1;
		}
		return age;
	}

	/**
	 * This utility method provides a map of a collection elements against keys
	 * formed by provided field names of the element object's class
	 * 
	 * eg: If i want to prepare Map of planCode , and Plan
	 * 
	 * list of plan object and planCode
	 */
	public static <E> Map<String, Object> getMap(
			final Collection<E> collection, final String... keyFieldNames) {

		final Map<String, Object> utilMap = new ConcurrentHashMap<String, Object>();

		for (final Object element : collection) {
			String key = null;
			key = generateKey(element, keyFieldNames);
			utilMap.put(key, element);
		}

		return utilMap;
	}

	/**
	 * @param element
	 * @param keyFieldNames
	 */
	public static String generateKey(final Object element,
			final String... keyFieldNames) {

		final StringBuilder key = new StringBuilder();
		for (final String fieldName : keyFieldNames) {
			final Method[] methods = element.getClass().getMethods();
			for (final Method method : methods) {
				if (method.getName().equalsIgnoreCase("get" + fieldName)) {
					try {
						key.append(method.invoke(element));
					} catch (final IllegalAccessException e) {
						e.printStackTrace();
					} catch (final IllegalArgumentException e) {
						e.printStackTrace();
					} catch (final InvocationTargetException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return key.toString();
	}

	public static byte[] getBytesFromFile(File file) throws IOException {

		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static <E> String createCommaSeparatedString(Collection<E> list) {

		if (list != null) {
			StringBuilder csv = new StringBuilder();
			Iterator<E> itr = list.iterator();
			while (itr.hasNext()) {
				csv.append(itr.next());
				if (itr.hasNext()) {
					csv.append(",");
				}
			}
			return csv.toString();
		}
		return null;
	}

	public static String prepareExchangeSpecificUrl(String s, String subDomain) {

		String protocolDelim = "://";
		if (!StringUtils.isEmpty(s) && !s.contains("localhost")
				&& !StringUtils.isEmpty(subDomain)) { // for localhost
			if (s.contains("://")) { // if does not contain the protocal
				String[] token = s.split(protocolDelim);

				// XXX - hack to ignore subdomain replacement if the url does
				// not start with ex1.
				String existingSubdomain = token[1].substring(0,
						token[1].indexOf('.'));
				if (!StringUtils.equalsIgnoreCase(existingSubdomain, "ex1")
						&& !StringUtils.equalsIgnoreCase(subDomain, "ex1")) {
					return s;
				}

				String subToken = token[1].substring(token[1].indexOf('.'));
				StringBuffer sb = new StringBuffer();
				sb.append(token[0]);
				sb.append(protocolDelim);
				sb.append(subDomain);
				sb.append(subToken);
				return sb.toString();
			} else {
				// XXX - hack to ignore subdomain replacement if the url does
				// not start with ex1.
				String existingSubdomain = s.substring(0, s.indexOf('.'));
				if (!StringUtils.equalsIgnoreCase(existingSubdomain, "ex1")
						&& !StringUtils.equalsIgnoreCase(subDomain, "ex1")) {
					return s;
				}

				String subToken = s.substring(s.indexOf('.'));
				StringBuffer sb = new StringBuffer();
				sb.append(subDomain);
				sb.append(subToken);
				return sb.toString();
			}
		} else {
			return s;
		}
	}

	public static boolean isUrl(String s) {
		try {
			URL url = new URL(s);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public static void main(String args[]) {

		String a = prepareExchangeSpecificUrl(
				"http://localhost:8080/carrier-admin", "abc");
		System.out.println(a);

		String c = prepareExchangeSpecificUrl(
				"https://hap.phix-qa05.demo.hcinternal.net/staticContent/employer/common/min/theme/images",
				"ex1");
		System.out.println(c);
	}

	public static ServiceException getServiceException(Exception e) {
		if (e instanceof ServiceException) {
			return (ServiceException) e;
		}
		return new ServiceException(new Error("generic.error",
				"Internal Server Error : " + e.getLocalizedMessage(), e));
	}

	public static String getNewLineFormattedString(List<String> response) {
		StringBuilder result = new StringBuilder("");
		for (String str : response) {
			result.append(str);
			result.append("\n");
		}
		return result.toString();
	}

	public static Object evaluateExpression(String expression, Object data) {
		ExpressionParser parser = new SpelExpressionParser();
		if (data != null && expression != null) {
			expression = expression.replaceAll("\\.", "?.");
			Object value = parser.parseExpression(expression).getValue(data);
			if (value != null && value instanceof Collection
					&& ((Collection) value).isEmpty()) {
				return null;
			}
			return value;

		} else {
			return null;
		}
	}

	public static String appendQueryParam(String url, String paramName,
			String paramValue) {

		Map<String, String> paramMap = new HashMap<String, String>(1);
		paramMap.put(paramName, paramValue);

		return appendQueryParams(url, paramMap);
	}

	public static String appendQueryParams(String url,
			Map<String, String> params) {

		if (MapUtils.isEmpty(params)) {
			return url;
		}

		StringBuffer urlBuffer = new StringBuffer(url);
		for (String key : params.keySet()) {
			if (urlBuffer.indexOf("?") == -1) {
				urlBuffer.append("?" + key + "=" + params.get(key));
			} else if (urlBuffer.indexOf("&" + key + "=") == -1
					&& urlBuffer.indexOf("?" + key + "=") == -1) {
				int hashIndex = urlBuffer.indexOf("#");
				if (hashIndex != -1) {
					urlBuffer.insert(hashIndex,
							"&" + key + "=" + params.get(key));
				} else {
					urlBuffer.append("&" + key + "=" + params.get(key));
				}
			}
		}

		return urlBuffer.toString();
	}

	public static boolean isNullOrBlank(Object obj)
			throws IllegalAccessException {
		boolean blank = true;
		if (obj == null) {
			return blank;
		}
		for (Field f : obj.getClass().getDeclaredFields()) {
			if (Modifier.isStatic(f.getModifiers()))
				continue;
			f.setAccessible(true);
			if (f.get(obj) != null) {
				blank = false;
			}
		}
		return blank;
	}
}
