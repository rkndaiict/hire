/**
 * 
 */
package com.hcentive.xlsx.annotations.processor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcentive.xlsx.annotations.WBColumnRef;
import com.hcentive.xlsx.annotations.Workbook;
import com.hcentive.xlsx.utils.XLSXUtility;

/**
 * @author prashant
 * 
 */
public class AnnotationProcessor {

	private Logger logger = LoggerFactory.getLogger(AnnotationProcessor.class);

	public WBMappingInfo process(Class<?> clazz) throws Exception
	{
		logger.info("Scanning " + clazz.getCanonicalName() + " for annotations");
		Map<String, String> mappings = new HashMap<String, String>();
		WBMappingInfo mappingInfo = new WBMappingInfo();

		// check if class has valid workbook marker
		Workbook workbook = clazz.getAnnotation(Workbook.class);
		if (workbook == null) {
			throw new Exception("Class does not has a valid workbook mapping. It must be annotated with @Workbook.");
		}

		logger.debug("Class is annotated with @Workbook");

		// check if header string specified
		String header = workbook.header();
		if (StringUtils.isEmpty(header)) {
			logger.debug("Header Information not present.");
		} else {
			mappingInfo.setHeaders(header);
		}

		// process sheetIndex
		int[] sheetIndexes = workbook.sheetIndex();
		String[] sheetNames = workbook.sheetName();

		mappingInfo.setSheetIndexes(sheetIndexes);
		mappingInfo.setSheetNames(sheetNames);

		// process column mappings
		Field[] fields = clazz.getDeclaredFields();

		if (fields.length <= 0) {
			throw new Exception("Invalid Workbook Class, no fields could be found in the class.");
		}

		for (Field field : fields)
		{
			WBColumnRef columnRef = field.getAnnotation(WBColumnRef.class);
			if (columnRef != null) {
				mappings.put(columnRef.value(), field.getName());
			}

		}

		// finally if mappings is still empty, all the fields with default
		// column references.
		if (mappings.size() <= 0) {
			logger.warn("Field(s) not annotated with @WBColumnRef, going with default mapping.");
			for (int i = 0; i < fields.length; i++) {
				mappings.put(XLSXUtility.generateColumnRef(i), fields[i].getName());
			}

		}

		// set class object
		mappingInfo.setClazz(clazz);

		mappingInfo.setMappings(mappings);

		return mappingInfo;
	}

	// public static void main(String aa[])
	// {
	// System.out.println("working...");
	// // System.out.println(generateColumnRef(701));
	// Field[] fields = Premium.class.getDeclaredFields();
	// for(Field field: fields)
	// System.out.println(field.getName());
	//
	//
	// ArrayList<String> one = new ArrayList<String>();
	// ArrayList<String> two = new ArrayList<String>();
	//
	// one.add("Prashant");one.add("This");one.add("Is");
	// two.add("Prashant");two.add("This");two.add("Is");
	//
	// System.out.println(one.equals(two));
	// }
}
