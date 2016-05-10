/**
 * 
 */
package com.hcentive.xlsx.parser;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcentive.xlsx.annotations.Formatted;
import com.hcentive.xlsx.utils.XLSXUtility;



/**
 * @author prashant
 *
 */
public class XLSXCellValueProcessor implements CellValueProcessor {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object process(Integer rowNum, String cellReference, String value, Object destObject,String fieldName)
			throws Exception {
		logger.debug("Processing cell value.");
		Class<?> type = PropertyUtils.getPropertyType(destObject, fieldName);
		Formatted formatting = destObject.getClass().getDeclaredField(fieldName).getAnnotation(Formatted.class);
		logger.debug("Removing formatting from field (if any).");
		if(formatting !=null && formatting.value() != null && formatting.value().length >0)
		{
			String[]  removeChars = formatting.value();
			removeChars = removeChars[0].split("-");
			// remove all the formatting
			for(String formattingVal: removeChars){
				value = XLSXUtility.replaceAll(value, formattingVal, "").trim();
			}
		}
		
		logger.debug("Set value for the field in mapped row object.");
		PropertyUtils.setSimpleProperty(destObject, fieldName, ConvertUtils.convert(value, type));
		return value;
	}

}
