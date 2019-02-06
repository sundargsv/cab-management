package cabmgmt.persistence.dao.mongo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	//private constructor to prevent instantiation of util class
	private DateUtil() {};
	
	/**
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date toUtilDate(LocalDateTime localDateTime) {
		
		if(localDateTime!=null) {
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date toUtilDate(LocalDate localDate) {
		
		if(localDate!=null) {
			return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		
		if(date!=null) {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		}
		else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate toLocalDate(Date date) {
		
		if(date!=null) {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		else {
			return null;
		}
	}
}
