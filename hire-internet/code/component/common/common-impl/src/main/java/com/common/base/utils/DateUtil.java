package com.common.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.management.RuntimeErrorException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateUtil {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static String DATE_PATTERN_EXPORT = "dd-MM-yyyy";
    public static String DATE_PATTERN_APPLICANT_DOB = "dd-MMM-yyyy";
    public static String DATE_PATTERN_MM_DD_YYYY = "MM-dd-yyyy";
    public static String DATE_PATTERN_MM_DD_YYYY_SLASHED = "MM/dd/yyyy";
    public static String DATE_PATTERN_D_MMM_YYYY= "d MMM, yyyy";
    public static String DATE_PATTERN_MMMM_D_YYYY= "MMMM d, yyyy";
    public static final String dateFormatWithTime = "MM/dd/yyyy HH:mm:ss";
    
    
    /**
     *  Returns "yyyy/mm/dd 00:00:00" if the execution-time is AM/PM,
     */
    public static Date getDateWithoutTime(final Date date) {
    	
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //calendar.set(Calendar.AM, 1);
        return calendar.getTime();
    }
    
    public static int getYear(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar.get(Calendar.YEAR);
    }

    /**
     * Returns "yyyy/mm/dd 00:00:00" irrespective of the execution-time(AM/PM)
     */
    public static Date getDateWithMidnightTime(final Date date) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    public static Date recalcauteDateFromToday(int Year, int month, int day) {

        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.YEAR, Year);
        c1.add(Calendar.MONTH, month);
        c1.add(Calendar.DAY_OF_YEAR, day);
        return getDateWithoutTime(c1.getTime());
    }
    
    public static Date recalcauteDateFromDate(Date original,int Year, int month, int day) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(original);
        c1.add(Calendar.YEAR, Year);
        c1.add(Calendar.MONTH, month);
        c1.add(Calendar.DAY_OF_YEAR, day);
        return getDateWithoutTime(c1.getTime());
    }

    public static String getConvertedDateWithTime(final Date date) {

        return new SimpleDateFormat(dateFormatWithTime).format(date);
    }

    public static Date getStringToDateWithTime(final String dateStr) throws ParseException {

        return new SimpleDateFormat(dateFormatWithTime).parse(dateStr);
    }

    public static Date getParsedDate(final String dateString, final String pattern) throws ParseException {

        if (StringUtils.isNotBlank(dateString)) {
            return new SimpleDateFormat(pattern).parse(dateString);
        }
        return null;
    }

    public static Date getParsedDate(final String dateString) throws ParseException {

        if (StringUtils.isNotBlank(dateString)) {
            return new SimpleDateFormat(DATE_PATTERN).parse(dateString);
        }
        return null;
    }

    public static String getFormattedDateString(final Date date) {

        String dateString = null;
        if (null != date) {
            dateString = new SimpleDateFormat(DATE_PATTERN).format(date);
        }
        return dateString;
    }

    public static String getFormattedDateString(final Date date, String Pattern) {

        String dateString = null;
        if (null != date) {
            dateString = new SimpleDateFormat(Pattern).format(date);
        }
        return dateString;
    }

    /**
     * clears the hours,minute,second,milliseconds of date
     * 
     * @param calendar
     */
    private static void setDateWithoutTime(final Calendar calendar) {

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Compare the date according to Year-Month-day only the value 0 if the time represented by the argument is equal to the time
     * represented by this Calendar; a value less than 0 if the time of date1 is before the time represented by the date2; and a
     * value greater than 0 if the time of date1 is after the time represented date2.
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateWithoutTime(final Date date1, final Date date2) {

        final Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        setDateWithoutTime(c1);
        final Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        setDateWithoutTime(c2);
        return c1.compareTo(c2);
    }

    public static String convertDateToString(Date date, String... format) {

        String dateStr = null;
        String dateFormat = "MM/dd/yyyy HH:mm:ss";
        if (null != format && format.length > 0) {
            dateFormat = format[0];
        }
        if (null != date) {
            DateFormat df = new SimpleDateFormat(dateFormat);
            dateStr = df.format(date);
        }
        return dateStr;
    }

    public static Date convertStringToDate(String dateStr, String... format) throws RuntimeErrorException {

        Date date = null;
        String dateFormat = "MM/dd/yyyy HH:mm:ss";
        if (null != format && format.length > 0) {
            dateFormat = format[0];
        }
        if (null != dateStr && !dateStr.isEmpty()) {
            DateFormat df = new SimpleDateFormat(dateFormat);
            try {
                date = df.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeErrorException(new Error("Error while converting String to date"));
            }
        }
        return date;
    }
    
    /**
     * Calculate the age of a person based on his Date of Birth.
     * 
     * @param dateOfBirth
     * @return age of the person as an int
     */
    public static int getAgeFromDob(Date dateOfBirth) {
    	if(dateOfBirth == null) {
    		throw new NullPointerException("Argument dateOfBirth cannot be null");
    	}
    	
    	Calendar dob = Calendar.getInstance();
    	dob.setTime(dateOfBirth);
    	
    	Calendar currentDate = Calendar.getInstance();
    	int age = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    	if(age < 0) {
    		return -1;
    	}
    	
    	if(currentDate.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
    		return age-1;
    	} else if(currentDate.get(Calendar.MONTH) == dob.get(Calendar.MONTH)) {
    		if(currentDate.get(Calendar.DATE) < dob.get(Calendar.DATE)) {
    			return age-1;
    		}
    	}
    	return age;
    }
    
    /**
     * Calculate the age of a person on a particular date 
     * using his Date of Birth.
     * 
     * @param dateOfBirth
     * @return age of the person as an int
     */
    public static int getAgeFromDob(Date dateOfBirth, Date onDate) {
    	if(dateOfBirth == null || onDate == null) {
    		throw new NullPointerException("Argument dateOfBirth and/or onDate cannot be null");
    	}
    	
    	Calendar dob = Calendar.getInstance();
    	dob.setTime(dateOfBirth);
    	Calendar currentDate = Calendar.getInstance();
    	currentDate.setTime(onDate);
    	
    	int age = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    	if(age < 0) {
    		return -1;
    	}
    	
    	if(currentDate.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
    		return age-1;
    	} else if(currentDate.get(Calendar.MONTH) == dob.get(Calendar.MONTH)) {
    		if(currentDate.get(Calendar.DATE) < dob.get(Calendar.DATE)) {
    			return age-1;
    		}
    	}
    	return age;
    }

    /**
     * Get the Current time
     * 
     * @param dateFormat
     * @return
     */
    public static String now(String dateFormat) {

        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }

    public static Date getYearStartDate(int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    
    /**
	 * Takes a date as input parameter and returns the start time of the day, i.e. date having time as 00:00:00
	 * 
	 * @param date the date
	 * @return Date
	 */
	public static Date getStartTimeOfDay(Date date){
	   	Calendar cal = Calendar.getInstance();
	   	cal.setTime(date);
	   	cal.set(Calendar.HOUR_OF_DAY, 0);
	   	cal.set(Calendar.MINUTE, 0);
	   	cal.set(Calendar.SECOND, 0);
	   	cal.set(Calendar.MILLISECOND, 0);
	   	return cal.getTime();
	}
	
	/**
	 * Takes a date as input parameter and returns the end time of the day, i.e. date having time as 23:59:59
	 * 
	 * @param date the date
	 * @return Date
	 */
	public static Date getEndTimeOfDay(Date date){
		Calendar cal = Calendar.getInstance();
	   	cal.setTime(date);
	   	cal.set(Calendar.HOUR_OF_DAY, 23);
	   	cal.set(Calendar.MINUTE, 59);
	   	cal.set(Calendar.SECOND, 59);
	   	cal.set(Calendar.MILLISECOND, 999);
	   	return cal.getTime();
	}
	
	public static Date addDaysToDate(Date date, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return getStartTimeOfDay(calendar.getTime());
	}

    public static Date convertXMLGregorianCalendarToDate(final XMLGregorianCalendar calendar){
        return calendar.toGregorianCalendar()!=null ? calendar.toGregorianCalendar().getTime() : null;
    }
    
    public static boolean isDateInBetweenDateRange(Date startDate, Date endDate, Date testDate) {
    	return ((startDate.before(testDate) || startDate.equals(testDate)) && (endDate.after(testDate) || endDate.equals(testDate)));
    }
    
    public static boolean isPastDate(Date targetDate, Date date){
    	Calendar cTargetDate = Calendar.getInstance();
    	cTargetDate.setTime(targetDate);
    	Calendar cDate = Calendar.getInstance();
    	cDate.setTime(date);
    	return compareDates(cTargetDate, cDate) < 0;
    }
    
    public static boolean isFutureDate(Date targetDate, Date date){
    	Calendar cTargetDate = Calendar.getInstance();
    	cTargetDate.setTime(targetDate);
    	Calendar cDate = Calendar.getInstance();
    	cDate.setTime(date);
    	return compareDates(cTargetDate, cDate) > 0;
    }
    
    public static boolean isSameDate(Date targetDate, Date date){
    	Calendar cTargetDate = Calendar.getInstance();
    	cTargetDate.setTime(targetDate);
    	Calendar cDate = Calendar.getInstance();
    	cDate.setTime(date);
    	return compareDates(cTargetDate, cDate) == 0;
    }
    
    public static boolean isPastDate(Date date){
    	return isPastDate(date, new Date());
    }
    
    public static boolean isFutureDate(Date date){
    	return isFutureDate(date, new Date());
    }
    
	/**
	 * Returns difference in days between 2 dates.
	 * 
	 * @param src
	 * @param target
	 * @return
	 */
	public static int getDiffInDays(Date dt1, Date dt2){
    
    	int diffInDays = (int) ((getDateWithoutTime(dt1).getTime() - getDateWithoutTime(dt2).getTime()) / (1000 * 60 * 60 * 24));
    	
    	return diffInDays;
    }
	
	public static int getDiffInMonths(Date toDate, Date fromDate){		
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fromDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(toDate);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    	return diffMonth;
    }
    
    
    public static boolean isTodayDate(Date date){
    	return isSameDate(date, new Date());
    }
    
    public static int compareDates(Calendar c1, Calendar c2){
    	if(DateUtils.isSameDay(c1, c2)){
    		return 0;
    	}
    	/*if(	c1.get(Calendar.ERA) < c2.get(Calendar.ERA) || 
    		c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR) || 
    		c1.get(Calendar.DAY_OF_YEAR) < c2.get(Calendar.DAY_OF_YEAR))
    		return -1;
    	return 1;*/
    	
    	return c1.compareTo(c2);
    	
    }
    
    public static void main(String[] args) {
    	
    	
    	
    }
    
    public static Integer daysDifference(final Date fromDate,final  Date toDate){
    	if(fromDate== null || toDate==null){
    		return null;
    	}
    	return Days.daysBetween(new DateTime(fromDate), new DateTime(toDate)).getDays();
    }

}
