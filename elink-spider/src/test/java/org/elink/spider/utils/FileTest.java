package org.elink.spider.utils;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class FileTest {
	@Test
	public void test() throws IOException{
		String filepath = "F:\\v11-test-space\\test\\tag.txt";
		FileUtils out = new FileUtils(filepath, "append");
		out.writeLine("1h1");
		out.close();
	}
}
