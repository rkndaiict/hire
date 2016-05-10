/**
 * 
 */
package com.hcentive.xlsx.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author prashant
 * Classes annotated with this will be scanned for workbook mapping.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Workbook {

	/**
	 * Optional attribute to capture comma separated list of workbook header column names.
	 * The workbook will be validated for header columns in the order specified in header attribute.
	 * @return
	 */
	public String header () default "";
	
	/**
	 * Attribute to capture sheet index (starting from 0) this POJO will be mapped to.
	 * @return
	 */
	public int[] sheetIndex() default -1;
	
	/**
	 * Attribute to capture sheet name, this POJO will be mapped to. This will not be used in parsing and has been provided only for readability purpose.
	 * @return
	 */
	public String[] sheetName() default "";
}
