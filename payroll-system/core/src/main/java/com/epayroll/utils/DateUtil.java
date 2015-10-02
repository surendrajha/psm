package com.epayroll.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil {
	private Logger logger = Logger.getLogger(DateUtil.class.getCanonicalName());
	private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	public static Date getDateFromString(String dateString, String dateFormat) {
		Date date = null;
		if (dateString != null && !dateString.equalsIgnoreCase("")) {
			DateUtil du = new DateUtil();
			du.logger.info("String Date : " + dateString);
			if (dateFormat == null) {
				dateFormat = DEFAULT_DATE_FORMAT;
			}
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);

			try {
				date = format.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			du.logger.info("Converted Date is : " + date);
		}
		return date;
	}

	public static String getStringFromDate(Date date, String dateFormat) {
		String strDate = null;
		if (date != null) {
			DateUtil du = new DateUtil();
			du.logger.info("Date : " + date);
			if (dateFormat == null) {
				dateFormat = DEFAULT_DATE_FORMAT;
			}
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);

			strDate = format.format(date);
			du.logger.info("Converted String Date is : " + strDate);
		}
		return strDate;
	}

	public static int diff(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);
		int diffDay = 0;

		if (c1.before(c2)) {
			diffDay = countDiffDay(c1, c2);
		} else {
			diffDay = countDiffDay(c2, c1);
		}

		return diffDay;
	}

	private static int countDiffDay(Calendar c1, Calendar c2) {
		int returnInt = 0;
		while (!c1.after(c2)) {
			c1.add(Calendar.MONTH, 1);
			returnInt++;
		}

		if (returnInt > 0) {
			returnInt = returnInt - 1;
		}

		return (returnInt);
	}

}
