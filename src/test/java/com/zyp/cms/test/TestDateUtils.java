package com.zyp.cms.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

import com.zyp.cms.utils.DateUtils;

public class TestDateUtils {
	 Scanner sc = new Scanner(System.in); 
	 @Test
	 public void testGetday() throws Exception {
		String next = sc.next();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Long getday = DateUtils.getday(format.parse(next));
		System.out.println(getday);
	}
	@Test
	public void testGetAge() {
		Date date = new Date(100,11,2);
		int age = DateUtils.getAge(date);
		System.out.println(age);
	}
	@Test
	public void testGetRemainDays() {
		Date date = new Date(120,0,1);
		int remainDays = DateUtils.getRemainDays(date);
		System.out.println(remainDays);
	}
	@Test
	public void testIsToday() {
		Date date=new Date(119,12,7);
		boolean today = DateUtils.isToday(date);
		System.out.println(today);
		boolean today2 = DateUtils.isToday(new Date());
		System.out.println(today2);
	}
	@Test
	public void testGetBeginOfMonth() {
		Date beginOfMonth = DateUtils.getBeginOfMonth();
		System.out.println(beginOfMonth);
	}
	@Test
	public void testGetEndOfMonth() {
		Date endOfMonth = DateUtils.getEndOfMonth();
		System.out.println(endOfMonth);
	}
	
	@Test
	public void testIsThisWeek() {
		Date date=new Date(120,01,31);
		DateUtils.isThisWeek(date);
		
	}
}
