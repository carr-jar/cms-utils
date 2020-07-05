package com.zyp.cms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Future;


/**
 * 关于日期的工具类
 * @author Administrator
 *
 */

public class DateUtils {
	/**
	 * 一天有多少毫秒
	 * 
	 */
	static final int millSecondsPerDay=1000*60*60*24;
	/**
	 * 计算剩余天数
	 * @param date
	 * @throws Exception
	 */
	public static Long getday(Date date) {
		Long s = (date.getTime())/(3600*24-System.currentTimeMillis());
		return s;
	}
	/**
	 * 计算年龄
	 */
	public static int getAge(Date birthday) {
		Calendar calendar = Calendar.getInstance();
		//计算出生的年月日
		calendar.setTime(birthday);
		int birYear = calendar.get(Calendar.YEAR);
		int birMONTH = calendar.get(Calendar.MONTH);
		int birDATE = calendar.get(Calendar.DATE);
		
		//计算当前的年月日
		calendar.setTime(new Date());
		int carrYEAR = calendar.get(Calendar.YEAR);
		int carrMONTH = calendar.get(Calendar.MONTH);
		int carrDATE = calendar.get(Calendar.DATE);
		//获取年龄
		int age=carrYEAR-birYear;
		//如果当前的月份小，则年龄减去1
		if(carrMONTH<birMONTH) {
			age--;
		}else if(carrMONTH==birMONTH && carrDATE<birDATE) {
			age--;
		}
		return age;
	}
	/**
	 * 剩余多少天
	 * @return
	 */
	public static int getRemainDays(Date future) {
		return (int )((future.getTime()- new Date().getTime())/millSecondsPerDay);
	}
	/**
	 * 判断是否为当天
	 */
	public static boolean  isToday(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formatSrc = dateFormat.format(date);
		String formatToday = dateFormat.format(new Date());
		return formatSrc.equals(formatToday);
	}
	/**
	 * 获取当月的月初
	 */
	public static Date getBeginOfMonth() {
		//获取日历的实例
		Calendar instance = Calendar.getInstance();
		//设置成当前的时间
		instance.setTime(new Date());
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.HOUR, 0);
		instance.set(Calendar.AM_PM, Calendar.AM);
		instance.set(Calendar.DATE, 1);
		
		return instance.getTime();
	}
	/**
	 * 获取当前月的月末
	 */
	public static Date getEndOfMonth() {
		//获取日历的实例
		Calendar instance = Calendar.getInstance();
		///设置成当前时间
		instance.setTime(new Date());
		instance.add(Calendar.MONTH, 1);
		
		//先设置成月初
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.HOUR, 0);
		instance.set(Calendar.AM_PM, Calendar.AM);
		instance.set(Calendar.DATE, 1);
		instance.set(Calendar.DATE, 1);
		
		//减去一秒 变成当月的月末
		instance.add(Calendar.SECOND, -1);
		return instance.getTime();
	}
	/**
	 * 
	 */
	public static boolean  isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day+1+1);// 后面的+1是因为从周日开始

		// 本周一的日期

		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7-day+1);

		// 本周星期天的日期

		System.out.println(format.format(lastDayOfWeek.getTime()));
		
		return (date.getTime()<lastDayOfWeek.getTime().getTime() &&
				date.getTime()>firstDayOfWeek.getTime().getTime() );

	}
	public static Date randomDate(String beginDate,String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);
			if(start.getTime()>=end.getTime()) {
				return null;
			}
			long date = random(start.getTime(),end.getTime());
			return new Date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static long random(long begin,long end) {
		long rtn = begin + (long)(Math.random()*(end-begin));
		if(rtn==begin || rtn==end) {
			return random(begin,end);
		}
		return rtn;
	}
}
