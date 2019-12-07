package com.zyp.cms.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

public class FileUtils {
	/**
	 * 获取文件的拓展名
	 */
	public static String getSuffixName(String fileName) {
		//获取最后一个点的位置
		int dotPos = fileName.lastIndexOf(".");
		if(dotPos<0) {
			return "";
		}
		return fileName.substring(dotPos);
	}
	/**
	 * 删除文件
	 */
	public static void delFile(String fileName) {
		File file = new File(fileName);
		//文件不存在
		if(!file.exists()) {
			System.out.println("文件不存在");
			return ;
		}
		//如果是目录
		if(file.isDirectory()) {
			//递归删除子目录或者文件
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				String childFileName=fileName+"\\"+list[i];
				delFile(childFileName);
			}
		}
		//如果是文件，或者删除子目录之后，删除本身
		file.delete();
	}
	/**
	 * 获取系统环境变量值
	 */
	public static void getProperty(String key) {
		Properties properties = System.getProperties();
		//获取环境变量
		Set<Object> keySet = properties.keySet();
		for (Object hkey : keySet) {
			System.out.println(hkey);
			System.out.println(properties.getProperty((String) hkey));
		}
//		return properties.getProperty(key);
	}
	/*
	 * 获取系统的环境变量
	 * 
	 */
	public static void getEnv(String key) {
		Map<String, String> getenv = System.getenv();
		getenv.forEach((hkey,hvalue)->{
			System.out.println(hkey);
			System.out.println(hvalue);
		});
	}
	/**
	 * 获取文件的大小
	 */
	public static long getSize(String fileName) {
		File file = new File(fileName);
		if(!file.exists()||!file.isFile()) {
			return 0;
		}
		return file.length();
	}
	/**
	 * 比较两个文件夹内容是否相同
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String comparePath(String src,String dst) throws Exception {
		String hh="";
		File srcfile = new File(src);
		File dstfile = new File(dst);
		if(!srcfile.exists()) {
			hh="源文件不存在";
		}
		if(!dstfile.exists()) {
			hh="目标文件不存在";
		}
		if(srcfile.isFile()&&dstfile.isFile()) {
			if(srcfile.length()!=dstfile.length()) {
				hh="文件长度不一致";
			}else {
				byte[] md5src = DigestUtils.md5(new FileInputStream(srcfile));
				byte[] md5dst = DigestUtils.md5(new FileInputStream(dstfile));
				String strMd5Src = new String(md5src);
				String strMd5Dst = new String(md5dst);
				if(!strMd5Src.equals(strMd5Dst)) {
					hh="文件内容不一致";
				}
			}
		}
		return hh;
	}
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String read(String fileName) throws IOException {
		
		//用于存储文件内容
		StringBuilder sb = new StringBuilder();
		
		// 创建文件对象
		File file = new File(fileName);
		
		//创建文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 创建缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
		String ln=null;
		//按行读入
		while ((ln= br.readLine())!=null) {
			sb.append(ln);
		}
		
		closeStream(br,fis);
		
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @param file
	 * @return	
	 * @throws IOException 
	 */
	public static List<String> readByLines(String fileName) throws IOException {
		
		//用于存储文件内容
		List<String> lines = new ArrayList();
		
		// 创建文件对象
		File file = new File(fileName);
		
		//创建文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 创建缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String ln=null;
		//按行读入
		while ((ln= br.readLine())!=null) {
			//sb.append(ln);
			lines.add(ln);
		}
		
		closeStream(br,fis);
		
		return lines;
	}
	
	
	
	/**
	 *  关闭流
	 * @param stream
	 * @throws IOException 
	 */
	public static void closeStream(Closeable ... stream) throws IOException {
		
		for (int i = 0; i < stream.length; i++) {
			stream[i].close();
		}
	}
	
	/**
	 * 复制文件
	 * @param srcFile  源文件
	 * @param dstFile  目标文件
	 * @throws IOException 
	 */
	public synchronized static void copy(String srcFileName ,String dstFileName) throws IOException {
		// 源文件
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos  = new FileOutputStream(dstFile);
		byte b[]=new byte[1024];
		
		while(fis.read(b)>0) {
			fos.write(b);
		}
		closeStream(fos,fis);
	}
	
}

