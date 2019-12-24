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
	public static boolean isBlank(String str) {
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
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String regex = "^\\d{1,}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
	}
	/**
	 * 判断是否有数字
	 */
	public static boolean isHaveNumber(String str) {
		char[] charArray = str.toCharArray();
		for (char c : charArray) {
			if(c>='0' && c<='9') {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断手机号是否为数值，是否长度为11位，开始位必须是1.
	 */
	public static boolean judgeTelephoneIsOk(String src){
		String regex="^[1]\\d{10}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(src);
		boolean find = matcher.find();
		return find;
		
	}
	/**
	 * 验证是否是URL
	 * @param url
	 * @return
	 */
	public static boolean isHttpUrl(String str){
		
		 //转换为小写
        str = str.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
               + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184               
                 + "|" // 允许IP和DOMAIN（域名） 或单域名
                 + "[0-9a-z]*"  // 或单域名
                 + "|" // 允许IP和DOMAIN（域名） 或单域名
                 + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
                 + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
                + "[a-z]{2,6})" // first level domain- .com or .museum  
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name  
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
        return  str.matches(regex);	
	}
	/**
          * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137;
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s = s * EARTH_RADIUS * 1000;
       
        return s;
    }
}
