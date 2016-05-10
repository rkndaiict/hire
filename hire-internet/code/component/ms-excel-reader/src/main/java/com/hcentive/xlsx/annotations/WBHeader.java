/**
 * 
 */
package com.hcentive.xlsx.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author prashant
 *
 * This annotations can be used to specify the header for workbook.
 * The workbook will be validated for header columns in the order specified in values attribute.  
 */
@Target(ElementType.TYPE)
public @interface WBHeader {

	public String [] values();
}
