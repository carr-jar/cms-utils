package com.zyp.cms.test;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zyp.cms.utils.DateUtils;
import com.zyp.cms.utils.StringUtils;


public class TestStringUtils {
	
	StringUtils stringutils;
	@Before
	public void init() {
		stringutils = new StringUtils();
	}
	@Test
	public void Demo() {
		String str="";
		boolean blank = stringutils.isBlank(str);
		Assert.assertTrue(blank);
	}
	@Test
	public void testGetCharAndNumr() {
		String charAndNumr = StringUtils.getCharAndNumr(10);
		System.out.println(charAndNumr);
		Assert.assertTrue(charAndNumr.length()==10);
	}
	@Test
	public void testGetRandomStr() {
		String randomStr = StringUtils.getRandomStr(10);
		System.out.println(randomStr);
		Assert.assertTrue(randomStr.length()==10);
	}
	
	@Test
	public void testGetGb2312() throws UnsupportedEncodingException {
		String gb2312 = StringUtils.getGb2312(10);
		System.out.println(gb2312);
	}
	@Test
	public void testIsEmail() {
		Boolean email = StringUtils.isEmail("1asdf@qq.com");
		System.out.println(email);
	}
}
