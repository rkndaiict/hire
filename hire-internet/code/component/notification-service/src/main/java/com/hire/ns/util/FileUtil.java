package com.hire.ns.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil implements Cloneable {

	/** The instance. */
	private static FileUtil instance = null;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	private static final String TOMCAT_BASE_PATH = System.getProperty("catalina.base");
	
	/**
	 * Initialize the velocity engine.
	 */
	static {
		try{
			Velocity.setProperty("runtime.log", TOMCAT_BASE_PATH + "/logs/velocity.log");
			Velocity.setProperty("runtime.log.logsystem.class","org.apache.velocity.runtime.log.NullLogSystem");
			Velocity.init();
		}catch(Exception e){
			LOGGER.error("Exception occured while initializing Velocity engine", e);
		}
	}

	/**
	 * Default constructor for FileUtil.
	 */
	private FileUtil() {
		super();
	}

	/**
	 * This is to return single instance of FileUtil class.
	 * 
	 * @return - instance of FileUtil
	 */
	public static FileUtil getInstance() {
		synchronized (FileUtil.class) {
			if (null == instance) {
				instance = new FileUtil();
			}
		}
		return instance;
	}

	/**
	 * This method overrides clone method of Object class to prevent cloning of
	 * FileUtil.
	 * 
	 * @return - nothing.
	 * @throws CloneNotSupportedException
	 *             -CloneNotSupportedException
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * This functions checks if exists at given path.
	 * 
	 * @param filePath
	 *            - path of file
	 * @return - true if file exists
	 */
	public static boolean isFileExist(String filePath) {
		File theFile = new File(filePath);
		return theFile.exists();
	}

	/**
	 * This Method Converts InputStream to String.
	 * 
	 * @param is
	 *            InputStream
	 * @return String
	 * @throws IOException
	 *             if stream (is not null and) cannot be closed.
	 */
	public static String convertStreamToString(final InputStream is) throws IOException {
		if (is != null) {
			StringBuffer sb = new StringBuffer();
			String line;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}
	
	public static String velocityEval(final String template, final Map<String, Object> parameters) throws Exception{
		return velocityEval(template, parameters, null);
	}

	/**
	 * This Method is used to format/parse data in template by using Velocity
	 * Utility Class.
	 * 
	 * @param template
	 *            final review template
	 * @param parameters
	 *            parameters to be passed
	 * @return String
	 * @throws Exception
	 *             - Exception
	 */
	public static String velocityEval(final String template, final Map<String, Object> parameters, String subDomain) throws Exception {
		if (template == null || template.trim().isEmpty() || parameters == null || parameters.isEmpty()) {
			return template;
		} else {
			LOGGER.info("velocityEval: template :"+template);
			StringWriter writer = new StringWriter();
			VelocityContext context = new VelocityContext();
			Set<String> keySet = parameters.keySet();
			Iterator<String> iter = keySet.iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				Object value = parameters.get(key);
				// Finding Url
				if(value instanceof String){
//					if(CommonUtil.isUrl(value.toString())){
//						value = CommonUtil.prepareExchangeSpecificUrl(value.toString(), subDomain);
//					}
				}
				if(value instanceof Date) {
					value = new SimpleDateFormat("MM/dd/yyyy").format(value);
				}
				context.put(key.toString(), value);
				LOGGER.info("Key :"+key+" Value :"+value);
			}
			/*Velocity.setProperty("runtime.log", TOMCAT_BASE_PATH + "/logs/velocity.log");
			Velocity.setProperty("runtime.log.logsystem.class","org.apache.velocity.runtime.log.NullLogSystem");
			Velocity.init();*/
			Velocity.evaluate(context, writer, "convertString", template);
			LOGGER.info("String returned by Velocity >>> "+writer.getBuffer().toString());
			return writer.getBuffer().toString();
		}
	}
	

	/**
	 * This Method is used to remove file.
	 * 
	 * @param fileName
	 *            -name of file
	 * @throws IOException
	 *             -IOException
	 */
	public static void removeFile(String fileName) throws IOException {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		} else {
			throw new IOException("File does-not exists");
		}
	}

	/**
	 * This method converts file to byte array.
	 * 
	 * @param file
	 *            - file to be converted
	 * @return - byte array
	 * @throws IOException
	 *             - error
	 */
	public static byte[] convertFileToByteArray(File file) throws IOException {
		byte[] bytes = null;
		if (null != file) {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int readNum = fis.read(buf);
			for (; readNum != -1;) {
				bos.write(buf, 0, readNum); // no doubt here is 0
				readNum = fis.read(buf);
			}
			bytes = bos.toByteArray();
			fis.close();
		}
		return bytes;
	}

	/**
	 * This method load the oppeFppe.properties file form server.
	 * 
	 * @return Properties - Properties
	 */
	public static Properties loadApplicationProperties() {
		Properties sysProps = null;
		InputStream fis = null;
		try {
			sysProps = System.getProperties();
			fis = FileUtil.class.getResourceAsStream("ns.properties");
		    sysProps.load(fis);
		} catch (IOException e) {
			LOGGER.debug("DASH_GENERIC_ERROR");
		} finally{
			try {
				if(null != fis)
				fis.close();
			} catch (IOException e) {
			}
		}
		return sysProps;
	}
}
