
package com.zyp.cms.utils;

import java.util.Random;

public class Demo {
	public static void main(String[] args) {
		String str = "";

		 int hightPos; //

		 int lowPos;

		 Random random = new Random();

		 hightPos = (176 + Math.abs(random.nextInt(39)));

		 lowPos = (161 + Math.abs(random.nextInt(93)));

		 byte[] b = new byte[2];

		 b[0] = (Integer.valueOf(hightPos)).byteValue();

		 b[1] = (Integer.valueOf(lowPos)).byteValue();

		 try {

		 str = new String(b, "GBK");

		 } catch (Exception e) {

		 e.printStackTrace();

		 System.out.println("错误");

		 }

		 System.out.println(str.charAt(0));
	}
}
