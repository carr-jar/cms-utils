package com.zyp.cms.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.zyp.cms.utils.FileUtils;

public class TestFileUtils {
	@Test
	public void testGetSuffixName() {
		String suffixName = FileUtils.getSuffixName("F:\\360zip\\config\\zconfig.xml");
		System.out.println(suffixName);
	}
	@Test
	public void testDelFile() {
		FileUtils.delFile("F:\\");
	}
	@Test
	public void testGetProperty() {
		FileUtils.getProperty("");
	}
	@Test
	public void testGetEnv() {
		FileUtils.getEnv("");
	}
	@Test
	public void testGetSize() {
		long size = FileUtils.getSize("F:\\考试录屏\\作业01.mp4");
		System.out.println(size/1024);
	}
	@Test
	public void testComparePath() throws Exception {
		String src="F:\\hh\\1.txt";
		String dst="F:\\JAVA\\1.txt";
		FileUtils.comparePath(src, dst);
	}
	@Test
	public void testReadLines() throws IOException {
		
		List<String> lines = FileUtils.readByLines("F:\\Notepad++\\contextMenu.xml");
		
		lines.forEach(x->{System.out.println(" x is " + x);});
	}
	
	
	@Test
	public void testCopy() throws IOException {
		String src="D:\\project\\07b\\07b.zip";
		String dst="D:\\project\\07b\\07b2.zip";
		FileUtils.copy(src,dst );
	} 
	
}
