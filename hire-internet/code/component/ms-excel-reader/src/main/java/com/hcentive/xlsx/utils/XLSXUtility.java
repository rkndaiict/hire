/**
 * 
 */
package com.hcentive.xlsx.utils;

import org.apache.commons.lang.StringUtils;



/**
 * @author prashant
 *
 */
public class XLSXUtility {
	
	public static String regExSpecialChars = "~@#$^&*()-_+=[]{}|,.?:\\";

	/**
	 * Get Excel Column reference for an index 
	 * @param columnIndex
	 * @return
	 */
	public static String generateColumnRef(int columnIndex)
	{
		if(columnIndex <26)
			return ((char)(65+ columnIndex))+"";
		else
			return generateColumnRef((columnIndex / 26)-1) + ((char)(65+ (columnIndex% 26)))+"";	
	}
	
	public static int generateColumnIndex(String columnRef)
	{
		int index = 0;
		for(int i=0;i<columnRef.length();i++)
			index += (26* i)+ (((int)columnRef.charAt(i)) -65);
		
		return index;
	}
	
	/**
	 * Cell reference is the concatenation of Column reference (i.e. A,B, C etc) with the row# starting with zero
	 * This methods extracts that alphabetic part from the cell reference
	 * @param cellReference
	 * @return
	 */
	public static String resolveColumnReference(String cellReference, int rowNum)
	{
		return cellReference.substring(0, cellReference.indexOf((rowNum+1)+""));
	}
	
	/**
	 * This method is replacement for String.replaceAll (handles Regex constructs e.g. $,. etc)
	 * @param source
	 * @param regEx
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(String source,String regEx,String replacement)
	{
		int index = source.indexOf(regEx);
		StringBuffer sb = new StringBuffer(source);
		StringBuffer tmp = null;
		while(index != -1){
			tmp = new StringBuffer();
			tmp.append(sb.substring(0,index));
			tmp.append(sb.substring((index+regEx.length()),sb.length()));
			if(StringUtils.isNotEmpty(replacement))
				tmp.insert(index, replacement);
			index = tmp.indexOf(regEx);
			sb = new StringBuffer(tmp);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] aa)
	{
		
		System.out.println(resolveColumnReference("AH123", 122));
		
//		System.out.println("$325.50".replaceAll(StringEscapeUtils.escapeJava("$3"), ""));
//		System.out.println("32$5.50".indexOf("$"));
		String source = "* ab$ab3ab2$5ab.50ab";
		String replacement = "-";
		System.out.println(replaceAll(source, "ab", replacement));
		System.out.println(replaceAll("* 275.08", "*", "").trim());

//		}
		
//		System.out.println(sb.toString());
//		System.out.println("$345~@#$^&*()-_+=[]{}|,.?:\\".replaceAll("~@#$^&*()-_+=|,.?:\\", ""));
//		for(int i=0;i<100;i++)
//		{
//			String s = generateColumnRef(i);
//			System.out.print(s+"\t");
//			System.out.println(generateColumnIndex(s));
//		}
	}
}
