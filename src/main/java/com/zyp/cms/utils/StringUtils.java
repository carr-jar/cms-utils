package com.zyp.cms.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public boolean isBlank(String str) {
		if(str==null ||"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 随机字母数字
	 * @param length
	 * @return
	 */
	public static String getCharAndNumr(int length) {    
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		// 输出字母还是数字
		String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";	
		// 字符串	
		if ("char".equalsIgnoreCase(charOrNum)) {	    
		// 取得大写字母还是小写字母	    
			int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;	    
			val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				// 数字	    
				val += String.valueOf(random.nextInt(10));	
				}    
		}    
			return val;
	}
	/**
	 * 生成随机字母
	 * @param n
	 * @return
	 */
	public static String getRandomStr(int n) {
		Random random = new Random();
		//StringBuilder 线程不安全 但是执行效率高，效率高的原因在访问的时候不会加锁
		//StringBuffer 线程安全 但是执行效率低下
		//这里可以使用StringBuilder,一个函数的执行只能在一个线程内部执行
		//不会被多个线程同时访问，不会出现线程安全的问题
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			char randomchar=(char)('a'+random.nextInt(26));
			sb.append(randomchar);
		}
		return sb.toString();
	}
	/**
	 * 随机中文
	 * @throws UnsupportedEncodingException 
	 */
	public static String getGb2312(int n) throws UnsupportedEncodingException {
		String sb="";
		byte []word=new byte[2];
		for (int i = 0; i < n; i++) {
			Random random = new Random();
			word[0]=(byte) (0xA1+0x10+random.nextInt(39));
			word[1]=(byte) (0xA1+0x10+random.nextInt(39));
			String string = new String(word,"GBK");
			sb+=string;
		}
		return sb;
	}
	/**
	 * 验证邮箱
	 */
	public static Boolean isEmail(String email) {
		String regEx1 =
		"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

		Pattern p = Pattern.compile(regEx1);

		Matcher m = p.matcher(email);

		return m.matches();
	}
}
