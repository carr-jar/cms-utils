package com.zyp.cms.utils;

import java.util.Random;

public class StringUtils {
	public boolean isBlank(String str) {
		if(str==null ||"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
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
	 * 
	 * @param args
	 * @throws Exception
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		String charAndNumr = StringUtils.getCharAndNumr(10);
		System.out.println(charAndNumr);
		Random random = new Random();///随机数
		String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8","9", "a", "b", "c", "d", "e", "f" };
		// 生成第1位的区码
		int r1 = random.nextInt(3) + 11; //生成11到14之间的随机数
		String str_r1 = rBase[r1];
		// 生成第2位的区码
		int r2;
		if (r1 == 13) {
		r2 = random.nextInt(7); //生成0到7之间的随机数
		} else {
		r2 = random.nextInt(16); //生成0到16之间的随机数
		}
		String str_r2 = rBase[r2];
		// 生成第1位的位码
		int r3 = random.nextInt(6) + 10; //生成10到16之间的随机数
		String str_r3 = rBase[r3];
		// 生成第2位的位码
		int r4;
		if (r3 == 10) {
		r4 = random.nextInt(15) + 1; //生成1到16之间的随机数
		} else if (r3 == 15) {
		r4 = random.nextInt(15); //生成0到15之间的随机数
		} else {
		r4 = random.nextInt(16); //生成0到16之间的随机数
		}
		String str_r4 = rBase[r4];
		System.out.println(str_r1 + str_r2 + str_r3 + str_r4);
		// 将生成机内码转换为汉字
		byte[] bytes = new byte[2];
		//将生成的区码保存到字节数组的第1个元素中
		String str_r12 = str_r1 + str_r2;
		int tempLow = Integer.parseInt(str_r12, 16);
		bytes[0] = (byte) tempLow;
		//将生成的位码保存到字节数组的第2个元素中
		String str_r34 = str_r3 + str_r4;
		int tempHigh = Integer.parseInt(str_r34, 16);
		bytes[1] = (byte) tempHigh;
		String ctmp = new String(bytes,"gb2312"); //根据字节数组生成汉字
		System.out.println("生成汉字:" + ctmp);
	}
}
